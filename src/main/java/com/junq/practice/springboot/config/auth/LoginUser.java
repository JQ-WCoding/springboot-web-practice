package com.junq.practice.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션 생성될 수 있는 위치를 지정
// PARAMETER 선언된 객체에서만 사용할 수 있습니다
// 클래스 선어문에 쓸 수 있는 TYPE 등이 있습니다
@Target ( ElementType.PARAMETER )
@Retention ( RetentionPolicy.RUNTIME )
// LoginUser 어노테이션 생성
public @interface LoginUser {
}
