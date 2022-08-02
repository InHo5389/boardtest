package com.board.controller;

import com.board.entity.Board;
import com.board.repository.BoardRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BoardApiController {

    private final BoardRepository repository;

    public BoardApiController(BoardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false) String title,
                    @RequestParam(required = false) String content){
        if (StringUtils.isEmpty(title)&&StringUtils.isEmpty(content)){
            return repository.findAll();
        }else{
            return repository.findByTitleOrContent(title,content);
        }
    }

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard){
        return repository.save(newBoard);
    }
    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard,@PathVariable Long id){
        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(()->{
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @Secured("ROLE_VIEWER")
    @DeleteMapping("/boards/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }

}
