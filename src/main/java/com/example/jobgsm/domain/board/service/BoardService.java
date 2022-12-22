package com.example.jobgsm.domain.board.service;

import com.example.jobgsm.domain.board.dto.request.BoardRequestDto;
import com.example.jobgsm.domain.board.dto.response.BoardResponseDto;
import com.example.jobgsm.domain.board.entity.Board;
import com.example.jobgsm.domain.board.entity.repository.BoardRepository;
import com.example.jobgsm.global.exception.CustomException;
import com.example.jobgsm.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDto params){
        Board entity = boardRepository.save(params.toEntity());
        return entity.getBoardId();
    }
    public List<BoardResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"boardId","createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(BoardRequestDto params) {
        Board entity = boardRepository.findById(params.getBoardId()).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getBoardTitle(), params.getBoardContent(), params.getBoardWriter(),params.getBoardApplicant(),params.getBoardDate());
        return params.getBoardId();
    }

    //게시글 삭제
    @Transactional
    public void delete(Long boardId) {
        // 1. 게시글 조회
        Board entity = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}
