package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberWriterService {

    private final MemberRepository memberRepository;

    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Transactional
    public MemberDto create(RegisterMemberCommand command) {
        /*
            목표 - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
                - 닉네임음 10자를 넘길 수 없다.
                - 회원은 닉네임을 변경할 수 있다.
                    : 회원의 닉네임 변경이력을 조회 할 수 있어야 한다.
         */

        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        Member saveMember = memberRepository.save(member);

        saveMemberNicknameHistory(saveMember);

        return saveMember.toDto();
    }

    public void update(Long id, String nickname) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.updateNickname(nickname);

        saveMemberNicknameHistory(member);

        memberRepository.save(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        MemberNicknameHistory history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }
}
