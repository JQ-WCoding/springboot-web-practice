package com.junq.practice.springboot.web;

import com.junq.practice.springboot.service.posts.PostsService;
import com.junq.practice.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    // 머스테치로 인해 앞의 경로와 뒤의 파일확장자는 자동으로 지정
    // 앞 경로 src/main/resources/templates
    // .mustache
    @GetMapping ( "/" )
    public String index(Model model) {
        // 서버 템플릿 엔진에서 사용가능한 객체 model
        // 해당 결과를 posts로 index.mustache에 전달
        model.addAttribute( "posts", postsService.findAllDesc() );
        return "index";
    }

    @GetMapping ( "/posts/save" )
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    // PathVariable의 존재 이유! : Url 의 {} 영역의 값을 지정해주기 위해 해당 메소드의 파라미터에 정확히 어떤걸 넣을 것이다
    // 선언해서 확실하게 지정해주어야한다
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById( id );
        model.addAttribute( "post", dto );

        return "posts-update";
    }
}
