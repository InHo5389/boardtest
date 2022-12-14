package com.board.controller;

import com.board.entity.Board;
import com.board.entity.User;
import com.board.repository.UserRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserApiController {

    private final UserRepository repository;

    public UserApiController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all(){
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser,@PathVariable Long id){
        return repository.findById(id)
                .map(user -> {
                   // user.setTitle(newUser.getTitle());
                   // user.setContent(newUser.getContent());
                    //user.setBoards(newUser.getBoards());
                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());
                    for (Board board : user.getBoards()){
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                .orElseGet(()->{
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }

}
