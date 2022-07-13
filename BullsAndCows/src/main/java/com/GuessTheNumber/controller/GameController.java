package com.GuessTheNumber.controller;
import com.GuessTheNumber.data.GuessDao;
import com.GuessTheNumber.models.GameNumber;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guess")
public class GameController {

    private final GuessDao dao;

    public GameController(GuessDao dao) {
        this.dao = dao;
    }

    //Returns all the games that have happened.
    @GetMapping
    public List<GameNumber> all(){
        return dao.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameNumber create(@RequestBody GameNumber game){
        return dao.add(game);
    }
}
