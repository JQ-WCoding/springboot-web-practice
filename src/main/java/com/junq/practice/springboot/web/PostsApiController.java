package com.junq.practice.springboot.web;

import com.junq.practice.springboot.service.posts.PostsService;
import com.junq.practice.springboot.web.dto.PostsResponseDto;
import com.junq.practice.springboot.web.dto.PostsSaveRequestDto;
import com.junq.practice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    /**
     *  - @RequestBody 와 @ResponseBody 비교
     * ajax 를 통해 보내지는 json 등의 형태의 데이터를 필요로 하는가 혹은 받아와서 사용해야하는가에 따라 구분지어질 수 있다.
     * - @PathVariable 의 받아와야할 해당 파라미터를 다시 String, Integer 등 datatype 에 맞게 선언해주기 위함이다.
     * 화면을 이동할 필요가 없는 경우 view 페이지의 이동이 없기에 url 을 리턴할 필요가 없다
     * ajax 를 이용한 비동기 방식으로 인해 내가 필요로 하는 data를 받아와 다시 반환하여 해당 현재 페이지에 검색 등을 한 데이터를 다시 반환하여 view 에 보여줄 수 있도록 한다.
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update( id, requestDto );
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById( id );
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete( id );
        return id;
    }
}
