package com.junq.practice.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링부트 테스트를 위해 필요한 기능
// tomcat 이 필요없어져요!
// 내장되어있는 was 를 사용
// 자동 설정, 스프링 bean 읽기 생성 모두 자동
@SpringBootApplication
// JPA Auditing 활성화
// @EnableJpaAuditing
public class Application {
    // 앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
