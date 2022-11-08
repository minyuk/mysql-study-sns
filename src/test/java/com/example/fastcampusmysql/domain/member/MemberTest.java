package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    void update() {
        //given
        Member member = MemberFixtureFactory.create();
        String expected = "update";

        //when
        member.updateNickname(expected);

        //then
        assertThat(member.getNickname()).isEqualTo(expected);
    }

    @Test
    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    void updateMaxLength() {
        //given
        Member member = MemberFixtureFactory.create();
        String overMaxLengthName = "12345678910";

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> member.updateNickname(overMaxLengthName));
    }
}
