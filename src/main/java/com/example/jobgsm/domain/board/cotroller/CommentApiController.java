package com.example.jobgsm.domain.board.cotroller;

import com.example.jobgsm.domain.board.dto.request.CommentRequestDto;

import com.example.jobgsm.domain.board.dto.response.CommentResponseDto;
import com.example.jobgsm.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/board/comment")
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDto> findAll(){
        return commentService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> commentSave(@RequestBody CommentRequestDto comment, BindingResult bindingResult){
        commentService.saveComment(comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> commentDelete(@RequestBody CommentRequestDto params){
        commentService.deleteComment(params.getCommentId());
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<Void> commentEdit(@RequestBody CommentRequestDto params){
        commentService.editComment(params.getCommentId(),params);
        return ResponseEntity.ok().build();
    }

}
