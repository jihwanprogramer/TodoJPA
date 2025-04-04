package org.example.todojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * 생성일시.
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;

    /**
     * 수정일시.
     */
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
