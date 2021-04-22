package com.junq.practice.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// final 필드가 포함된 생성자 생성
// final 없는 필드는 생성자에 포함되지 않음
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
