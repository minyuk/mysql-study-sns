package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    private final Long id;

    private String nickname;

    private final String email;

    private final LocalDate birthday;

    private final LocalDateTime createdAt;

    private final static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);

        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);

        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .id(id)
                .email(email)
                .birthday(birthday)
                .nickname(nickname)
                .build();
    }

    public void updateNickname(String to) {
        Objects.requireNonNull(to);
        validateNickname(to);

        nickname = to;
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
