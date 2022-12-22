package com.example.jobgsm.domain.board.service;

import com.example.jobgsm.domain.board.dto.request.CommentRequestDto;
import com.example.jobgsm.domain.board.dto.response.CommentResponseDto;
import com.example.jobgsm.domain.board.entity.Comment;
import com.example.jobgsm.domain.board.entity.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 작성
    public Long saveComment(CommentRequestDto params) {
        Comment entity = commentRepository.save(params.toEntity());
        return params.getCommentId();
    }

    //댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    //댓글 조회
    public List<CommentResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"commentId","createdDate");
        List<Comment> list = commentRepository.findAll(sort);
        return list.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    //댓글 수정
    public void editComment(Long id, CommentRequestDto comment) {
        Comment findComment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        findComment.setCommentContents(comment.getCommentContents());
        commentRepository.save(findComment);
    }
}