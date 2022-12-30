package com.example.jobgsm.domain.board.service;

import com.example.jobgsm.domain.board.exception.BoardNotFoundException;
import com.example.jobgsm.domain.board.presentation.dto.request.BoardRequestDto;
import com.example.jobgsm.domain.board.entity.Board;
import com.example.jobgsm.domain.board.repository.BoardRepository;
import com.example.jobgsm.domain.board.presentation.dto.response.BoardResponseDto;
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
    public void save(BoardRequestDto params){
        boardRepository.save(params.toEntity());
    }
    public List<BoardResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"boardId","createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void update(BoardRequestDto params) {
        Board entity = boardRepository.findById(params.getBoardId()).orElseThrow(() -> new BoardNotFoundException("게시글 정보를 찾을 수 없습니다."));
        entity.update(params.getBoardTitle(), params.getBoardContent(),params.getBoardApplicant(),params.getBoardStartDate(), params.getBoardEndDate() );
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
