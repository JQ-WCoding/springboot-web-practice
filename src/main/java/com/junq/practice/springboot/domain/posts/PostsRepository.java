package com.junq.practice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    //@Repository 추가 불필요
    // Entity 와 Entity Repository 는 함께 위치해야한다 (한패키지에 같이 구성되어 있는 것이 좋다)
}
