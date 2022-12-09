package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {

    private final FollowRepository followRepository;

    public List<FollowDto> getFollowings(Long memberId) {
        return followRepository.findAllByFromMemberId(memberId).stream()
                .map(Follow::toDto).toList();
    }

    public List<FollowDto> getFollowers(Long memberId) {
        return followRepository.findAllByToMemberId(memberId).stream()
                .map(Follow::toDto).toList();
    }
}
