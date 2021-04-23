package com.junq.practice.springboot.web;

import com.junq.practice.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    // RequestPara(" ") 는 해당 메소드 파라미터로 받아야할 외부 파라미터를 지정해주는 역할을 한다.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
