package com.example.fastcampusmysql.domain.follow.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FollowDto(
        Long id,
        Long fromMemberId,
        Long toMemberId,
        LocalDateTime createdAt
) {
}
