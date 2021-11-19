package com.junq.practice.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith ( SpringRunner.class )
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class ProfileControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void profile은_인증없이_호출된다() throws Exception {
        String expected = "default";
        ResponseEntity<String> response = restTemplate.getForEntity( "/profile", String.class );
        // assertThat() -> 비교를 통해 문제 여부를 확인하는 메소드
        // 응답 상태가 HttpStatus 의 OK 인 200과 동일한지 확인
        assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );
        // expected 변수의 값 'default' 가 그대로 body 에 담겨 응답되는지 확인
        assertThat( response.getBody() ).isEqualTo( expected );
    }

}
