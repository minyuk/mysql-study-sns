package com.example.fastcampusmysql.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberNicknameHistoryDto(
        Long id,
        Long memberId,
        String nickname,
        LocalDateTime createdAt
) {
}
