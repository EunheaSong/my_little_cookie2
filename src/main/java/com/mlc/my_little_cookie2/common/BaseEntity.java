package com.mlc.my_little_cookie2.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class BaseEntity {
    @CreatedDate
    public LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    public LocalDateTime updatedAt = LocalDateTime.now();
    public Boolean isDeleted = false;
}
