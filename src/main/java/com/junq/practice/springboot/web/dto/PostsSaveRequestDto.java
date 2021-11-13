package com.junq.practice.springboot.web.dto;

import com.junq.practice.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Posts entity 클래스 외에 또 Dto 를 만든이유 -> view 단은 자주 변경하면서 필요할때마다 원하는 정보를 다르게 보이게 만들 수 있다.
// 하지만 Entity 클래스는 DB와 직접적으로 연결되는 공간이다. 잦은 변경은 아주 예민할 수 있다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
