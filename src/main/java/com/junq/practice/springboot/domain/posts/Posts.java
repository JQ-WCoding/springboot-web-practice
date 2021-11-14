package com.junq.practice.springboot.domain.posts;

import com.junq.practice.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// lombok 어노테이션은 중요도가 떨어져 클래스에서 최대한 멀게 사용됨
@NoArgsConstructor // 기본생성자 추가
@Getter
// @Entity 테이블과 링크될 클래스
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭 ex)SpringBoot = spring_boot
@Entity
public class  Posts extends BaseTimeEntity {
    // PK -> primary key
    @Id
    // auto increment 자동으로 하나씩 증가하는 역할을 해주기 위해선 GenerationType.IDENTITY 가 필요함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // VARCHAR(255)가 기본값이나 500으로 변경
    @Column(length = 500, nullable = false)
    private String title;

    // Column definition 을 통해 타입을 Text 로 변경
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
