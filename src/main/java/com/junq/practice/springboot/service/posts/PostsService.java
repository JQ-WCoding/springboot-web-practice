package com.junq.practice.springboot.service.posts;

import com.junq.practice.springboot.domain.posts.PostsRepository;
import com.junq.practice.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 해줌
// why ues? 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움 해결
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
