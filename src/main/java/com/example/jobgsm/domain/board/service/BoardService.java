package com.example.jobgsm.domain.board.service;

import com.example.jobgsm.domain.board.dto.BoardRequestDto;
import com.example.jobgsm.domain.board.dto.BoardResponseDto;
import com.example.jobgsm.domain.board.entity.Board;
import com.example.jobgsm.domain.board.entity.BoardRepository;
import com.example.jobgsm.domain.board.exception.CustomException;
import com.example.jobgsm.domain.board.exception.ErrorCode;
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
    public Long save(final BoardRequestDto params){
        Board entity = boardRepository.save(params.toEntity());
        return entity.getBoardId();
    }

    public List<BoardResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id","createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(final Long boardId, final BoardRequestDto params) {

        Board entity = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getBoardTitle(), params.getBoardContent(), params.getBoardWriter(),params.getBoardApplicant(),params.getBoardDate());
        return boardId;
    }

    //delete
    @Transactional
    public void deleteBoard(Long boardId) {
        // 1. 게시글 조회
        Board entity = boardRepository.findById(boardId).get();
        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}
