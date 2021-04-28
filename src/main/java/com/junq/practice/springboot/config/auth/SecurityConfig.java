package com.junq.practice.springboot.config.auth;

import com.junq.practice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 옵션 임시해제
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                // URL별 권환 관리 옵션의 시작
                .authorizeRequests()
                .antMatchers( "/", "/css/**", "/images/**", "/js/**", "/h2-console/**" ).permitAll()
                // 권한 관리 대상 지정 옵션
                // url, http 메소들별 관리 가능
                // permitAll() -> 전체 열람 권한 설정 가능
                // api 는 user 권한 가진 사람만 가능
                .antMatchers( "/api/v1/**" ).hasRole( Role.USER.name() )
                // 설정값 이외의 url
                .anyRequest().authenticated()
                // 로그아웃시 이동
                .and()
                .logout()
                .logoutSuccessUrl( "/" )

                .and()
                // 로그인 설정
                .oauth2Login()
                // 사용자 정보 가져오기
                .userInfoEndpoint()
                // 성공시 후속 조치를 위한 인터페이스 구현체 등록
                .userService( customOAuth2UserService );

    }
}
