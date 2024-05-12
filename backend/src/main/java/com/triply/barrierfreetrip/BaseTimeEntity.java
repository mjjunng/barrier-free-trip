package com.triply.barrierfreetrip;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속받는 클래스가 해당 클래스의 필드 인식하도록
@EntityListeners(AuditingEntityListener.class) // Auditing(자동으로 값 매핑) 기능
public class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;
}
