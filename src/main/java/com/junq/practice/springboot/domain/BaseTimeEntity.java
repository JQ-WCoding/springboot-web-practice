package com.junq.practice.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity 클래스들이 BaseTimeEntity 상속할 경우 필드들도 칼럼으로 인식
@MappedSuperclass
// Auditing 기능 포함
@EntityListeners ( AuditingEntityListener.class )
public class BaseTimeEntity {
    // 최초 생성일
    @CreatedDate
    private LocalDateTime createDate;
    // 수정일
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
