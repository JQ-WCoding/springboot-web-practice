package com.junq.practice.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// final 필드가 포함된 생성자 생성
// final 없는 필드는 생성자에 포함되지 않음
@RequiredArgsConstructor
public class HelloResponseDto {
    /*
    ex) HelloResponseDto(String name, int amount){
            this.name = name;
            this.amount =  amount;
        }
        와 같은 형식으로 final 이 붙은 모든 필드는 생성자로 만들어짐
     */
    private final String name;
    private final int amount;
}
