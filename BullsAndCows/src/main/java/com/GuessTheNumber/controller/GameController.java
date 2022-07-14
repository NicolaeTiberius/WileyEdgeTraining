package com.GuessTheNumber.controller;
import com.GuessTheNumber.data.GuessDao;
import com.GuessTheNumber.models.GameNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
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



    //Creates a game object - starts a game and generates an answer.
    //http://localhost:8080/app/begin/
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin(){
        return  dao.beginGame();
    }

    //Makes a guess by passing the user guess and game id.
    //http://localhost:8080/app/guess?userGuess=1234&GameId=1
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public String[] guess(int userGuess, int GameId){
        return dao.guess(userGuess,GameId);
    }

    @DeleteMapping("/app")
    public ResponseEntity delete(@PathVariable int id){
        if(dao.deleteById(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
