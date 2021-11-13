package com.junq.practice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    //@Repository 추가 불필요
    // Entity 와 Entity Repository 는 함께 위치해야한다 (한패키지에 같이 구성되어 있는 것이 좋다)

    //쿼리를 사용해 Posts 객체의 해당 내용을 전부 가져옴 SELECT p
    // id를 기준으로 내림차순하여 가져올 예정
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC ")
    List<Posts> findAllDesc();
}
