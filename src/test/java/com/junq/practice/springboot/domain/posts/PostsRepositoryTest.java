package com.junq.practice.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// send 와 findAll 기능 테스트
@RunWith ( SpringRunner.class )
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    // JUnit 단위 테스트가 끝날때 마다 수행되는 메소드 저장
    // 테스트간 데이터 침범을 막기 위해 사용
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 해당 기본 생성자 빌더를 사용해 insert/update 쿼리 실행
        postsRepository.save( Posts.builder()
                .title( title )
                .content( content )
                .author( "junlee36520620@gmail.com" )
                .build() );

        //when
        // 전체 데이터 조회
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get( 0 );
        assertThat( posts.getTitle() ).isEqualTo( title );
        assertThat( posts.getContent() ).isEqualTo( content );
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of( 2021, 3, 25, 20, 25, 0, 0 );
        postsRepository.save( Posts.builder()
                .title( "title" )
                .content( "content" )
                .author( "author" )
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get( 0 );

        System.out.println(">>>>>>>>>> createDate = " + posts.getCreateDate() + ", modifiedDate = " + posts.getModifiedDate());

        assertThat( posts.getCreateDate()).isAfter( now );
        assertThat( posts.getModifiedDate() ).isAfter( now );
    }
}
