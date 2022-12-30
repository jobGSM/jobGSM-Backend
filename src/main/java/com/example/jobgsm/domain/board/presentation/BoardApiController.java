package com.example.jobgsm.domain.board.presentation;

import com.example.jobgsm.domain.board.presentation.dto.request.BoardRequestDto;
import com.example.jobgsm.domain.board.presentation.dto.response.BoardResponseDto;
import com.example.jobgsm.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    //게시글 리스트 조회
    @GetMapping("/boards")
    public List<BoardResponseDto> findAll(){
        return boardService.findAll();
    }

    //게시글 생성
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BoardRequestDto params){
        boardService.save(params);
        return ResponseEntity.ok().build();
    }


    //게시글 수정
    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody BoardRequestDto params){
        boardService.update(params);
        return ResponseEntity.ok().build();
    }
    //게시글 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody @Nullable BoardRequestDto boardId){
        boardService.delete(boardId.getBoardId());
        return ResponseEntity.ok().build();
    }
}
