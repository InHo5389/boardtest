package com.board.repository;

import com.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title,String Content);
    List<Board> findByTitleAndContent(String title,String Content);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
