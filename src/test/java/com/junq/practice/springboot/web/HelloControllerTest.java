package com.junq.practice.springboot.web;

import com.junq.practice.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.test.web.servlet.ResultActions;

// JUnit 에 내장된 실행자 외에 다른 실행자를 실행
// Springboot test 와 JUnit 사이의 연결자
@RunWith ( SpringRunner.class )
// Web(Spring MVC)에 집중할 수 있는 어노테이션 @Controller @ControllerAdvice 사용가능
// @Service @Component @ Repository 사용 불가
@WebMvcTest ( controllers = HelloController.class, excludeFilters = {@ComponentScan.Filter ( type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class )} )
public class HelloControllerTest {
    @Autowired
    // 빈 (bean) 주입
    private MockMvc mvc;
    // 웹 API 테스트시 사용
    // Spring MVC 테스트의 시작점
    // HTTP Get, Post 등에 대한 API 테스트 가능

    @WithMockUser ( roles = "USER" )
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform( get( "/hello" ) ) // MockMvc 를 통해 /hello 주소로 get 요청
                // 체이닝 지원되어 여러검증 기능 선언 가능
                // status 검증
                .andExpect( status().isOk() ) // 여기서 ok 200
                // 결과 검증 "hello" 리턴하기 때문에 값이 맞는지 검증
                .andExpect( content().string( hello ) );
    }

    @WithMockUser ( roles = "USER" )
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get( "/hello/dto" )
                        // String 형만 등록가능, 숫자/날짜의 데이터도 모두 문자열로 변경하여야한다. -> String.valueOf() 메소드 사용
                        .param( "name", name )
                        .param( "amount", String.valueOf( amount ) ) )
                // 생태 확인 -> 200, 400 등
                .andExpect( status().isOk() )
                //JSON 응답값을 필드별로 검증
                .andExpect( jsonPath( "$.name", is( name ) ) )
                .andExpect( jsonPath( "$.amount", is( amount ) ) );
    }
}
