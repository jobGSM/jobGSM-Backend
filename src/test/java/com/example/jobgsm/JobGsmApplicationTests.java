package com.example.jobgsm;

import com.example.jobgsm.domain.board.entity.BoardRepository;
import com.example.jobgsm.domain.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class JobGsmApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void save() {
        // 1. 게시글 파라미터 생성
        Board params = Board.builder()
                .boardTitle("1번 게시글 제목")
                .boardContent("1번 게시글 내용")
                .boardWriter("도뎡이")
                .boardApplicant(10)
                .boardDate("2006-02-16")
                .build();

        // 2. 게시글 저장
        boardRepository.save(params);

        // 3. 1번 게시글 정보 조회
        Board entity = boardRepository.findById((long) 1).get();
        assertThat(entity.getBoardTitle()).isEqualTo("1번 게시글 제목");
        assertThat(entity.getBoardContent()).isEqualTo("1번 게시글 내용");
        assertThat(entity.getBoardWriter()).isEqualTo("도뎡이");
        assertThat(entity.getBoardApplicant()).isEqualTo(10);
        assertThat(entity.getBoardDate()).isEqualTo("2006-02-16");
    }

    @Test
    void findAll() {

        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        // 2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll();
    }

    @Test
    void delete() {
        // 1. 게시글 조회
        Board entity = boardRepository.findById((long) 7).get();
        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}