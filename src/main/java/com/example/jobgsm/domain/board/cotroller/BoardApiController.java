package com.example.jobgsm.domain.board.cotroller;

import com.example.jobgsm.domain.board.dto.BoardRequestDto;
import com.example.jobgsm.domain.board.dto.BoardResponseDto;
import com.example.jobgsm.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    //게시글 리스트 조회
    @RequestMapping("/boards")
    @GetMapping
    public List<BoardResponseDto> findAll(){
        return boardService.findAll();
    }

    //게시글 생성
    @PostMapping
    public Long save(@RequestBody final BoardRequestDto params){
        return boardService.save(params);
    }

    //게시글 수정
    @PatchMapping("/{boardId}")
    public Long save(@PathVariable final Long boardId,@RequestBody final BoardRequestDto params){
        return boardService.update(boardId,params);
    }
    

    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable final Long boardId){
        boardService.deleteBoard(boardId);
    }
}
