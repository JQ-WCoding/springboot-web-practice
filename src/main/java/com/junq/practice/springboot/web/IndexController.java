package com.junq.practice.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    // 머스테치로 인해 앞의 경로와 뒤의 파일확장자는 자동으로 지정
    // 앞 경로 src/main/resources/templates
    // .mustache
    @GetMapping ( "/" )
    public String index() {
        return "index";
    }

    @GetMapping ( "/posts/save" )
    public String postsSave(){
        return "posts-save";
    }
}