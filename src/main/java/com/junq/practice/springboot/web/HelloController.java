package com.junq.practice.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody를 각메소드마다 선언했던 것을 한번에 사용하게 해줌
// 컨트롤러를 JSON으로 반환하게 해줌
@RestController
public class HelloController {
    // HTTP method Get의 요청을 받은 API
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
