package com.board;

import com.board.entity.Board;
import com.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    BoardRepository boardRepository;

    @PostConstruct
    private void init(){
        Board board1 = new Board(1L, "바람과 함께 사라지다1", "감명깊다1");
        Board board2 = new Board(2L, "바람과 함께 사라지다2", "감명깊다2");
        Board board3 = new Board(3L, "바람과 함께 사라지다3", "감명깊다3");

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
    }
}
