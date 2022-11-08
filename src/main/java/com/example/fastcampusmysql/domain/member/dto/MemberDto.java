package com.example.fastcampusmysql.domain.member.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberDto(
        Long id,
        String email,
        String nickname,
        LocalDate birthday
) {
}
