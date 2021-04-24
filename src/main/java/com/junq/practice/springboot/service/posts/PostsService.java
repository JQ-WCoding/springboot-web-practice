package com.junq.practice.springboot.service.posts;

import com.junq.practice.springboot.domain.posts.Posts;
import com.junq.practice.springboot.domain.posts.PostsRepository;
import com.junq.practice.springboot.web.dto.PostsResponseDto;
import com.junq.practice.springboot.web.dto.PostsSaveRequestDto;
import com.junq.practice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 해줌
// why ues? 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움 해결
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save( requestDto.toEntity() ).getId();
    }

    @Transactional
    // update 데이터베이스에 쿼리를 제거하는 부분이 없음
    // JPA 영속성 컨텍스트 떄문 (엔티티를 영구 저장하는 환경)
    // Entity 객체의 값만 변경하면 Update 쿼리가 필요없음 = 더티체킹
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // lambda
        Posts posts = postsRepository.findById( id ).orElseThrow( () -> new IllegalArgumentException( "해당 게시글이 없습니다. id =" + id ) );

        posts.update( requestDto.getTitle(), requestDto.getContent() );

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById( id ).orElseThrow( () -> new IllegalArgumentException( "해당 게시글이 없습니다. id =" + id ) );
        return new PostsResponseDto( entity );
    }
}
