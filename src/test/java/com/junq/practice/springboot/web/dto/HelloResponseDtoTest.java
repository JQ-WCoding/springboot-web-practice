package com.junq.practice.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

// DTO 의 값이 제대로 넘어오는지 확인하기 위한 단위 test
public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;
        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        // 위의 필드값을 이용해 dto 를 새로 인스턴스화 한후 해당 값이 잘 적용되었는지 확인

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
