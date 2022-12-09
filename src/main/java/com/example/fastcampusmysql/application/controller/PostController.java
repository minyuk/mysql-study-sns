package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.GetTimelineUsecase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("posts")
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private final GetTimelineUsecase getTimelineUsecase;

    @PostMapping("")
    public Long create(@RequestBody PostCommand command) {
        return postWriteService.create(command);
    }

    @GetMapping("/members/{memberId}")
    public Page<Post> getPosts (@PathVariable Long memberId, Pageable pageable){
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsCursor(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/{memberId}/daily-post-counts/")
    public List<DailyPostCount> getDailyPostCounts(@PathVariable Long memberId, DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(memberId, request);
    }

    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(@PathVariable Long memberId, CursorRequest cursorRequest) {
        return getTimelineUsecase.excute(memberId, cursorRequest);
    }

}
