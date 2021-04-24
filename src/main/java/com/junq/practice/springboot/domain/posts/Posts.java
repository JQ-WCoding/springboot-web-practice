package com.junq.practice.springboot.domain.posts;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
// @Entity 테이블과 링크될 클래스
// 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭 ex)SpringBoot = spring_boot
@Entity
public class  Posts{
    // PK -> primary key
    @Id
    // auto increment 자동으로 하나씩 증가하는 역할을 해줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
