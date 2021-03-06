package com.junq.practice.springboot.service.posts;

import com.junq.practice.springboot.domain.posts.Posts;
import com.junq.practice.springboot.domain.posts.PostsRepository;
import com.junq.practice.springboot.web.dto.PostsListResponseDto;
import com.junq.practice.springboot.web.dto.PostsResponseDto;
import com.junq.practice.springboot.web.dto.PostsSaveRequestDto;
import com.junq.practice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// final 이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 해줌
// why ues? 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움 해결
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save( requestDto.toEntity() ).getId();
    }

    // update 데이터베이스에 쿼리를 제거하는 부분이 없음
    // JPA 영속성 컨텍스트 떄문 (엔티티를 영구 저장하는 환경)
    // Entity 객체의 값만 변경하면 Update 쿼리가 필요없음 = 더티체킹

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // lambda
        Posts posts = postsRepository.findById( id ).orElseThrow( () -> new IllegalArgumentException( "해당 게시글이 없습니다. id =" + id ) );
        // 해당 게시글의 제목과 내용을 update
        posts.update( requestDto.getTitle(), requestDto.getContent() );
        // 게시글 아이디만 반환한다
        return id;
    }

    public PostsResponseDto findById(Long id) {
        // Posts 클래스로 파라미터로 받은 아이디를 생성 및 초기화 하여
        Posts entity = postsRepository.findById( id ).orElseThrow( () -> new IllegalArgumentException( "해당 게시글이 없습니다. id =" + id ) );
        // PostsResponseDto 생성자에 entity 를 넘겨 새로 인스턴스화한다
        return new PostsResponseDto( entity );
    }

    // 스프링 부트 패키지에 있는 transactional 을 사용해야 한다
    // 쓰다보니 javax 에도 있던데 이거 쓰면 readOnly 추가 사용 불가
    // 트랜잭션 범위는 유지되고 조회 기능만 사용되어 속도 개선
    @Transactional (readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                // lambda 식 .map(posts -> new PostsListResponseDto(posts)) 와 동일
                .map( PostsListResponseDto::new )
                .collect( Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById( id )
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        // JpaRepository 에서 이미 delete 메소드 지원
        postsRepository.delete( posts );
    }
}
