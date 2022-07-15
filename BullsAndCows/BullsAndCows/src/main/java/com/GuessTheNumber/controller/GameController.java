package com.GuessTheNumber.controller;
import com.GuessTheNumber.data.GuessDao;
import com.GuessTheNumber.models.GameNumber;
import com.GuessTheNumber.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class GameController {

    private final GameService service;


    //USES THE SERVICE FOR THE METHODS SO THE CONTROLLER DOESNT ACTUALLY ACCESS DAO
    // BUT THE DAO ACCESSES SERVICE.


    public GameController(GameService service, GuessDao dao) {
        this.service = service;
    }


    //Creates a game object - starts a game and generates an answer.
    //http://localhost:8080/app/begin/
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin(){
        return  service.beginGame();
    }

    //Makes a guess by passing the user guess and game id.
    //http://localhost:8080/app/guess?userGuess=1234&GameId=1
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public GameNumber guess(int userGuess, int GameId){
        service.getAll();
        return service.guess(userGuess,GameId);


    }

    //Returns all the games that have happened.
    @GetMapping
    public List<GameNumber> all(){
        return service.getAll();
    }

    //Returns the game with the specific id
    @GetMapping("/gameId")
    public ResponseEntity<GameNumber>findbyId(@PathVariable int id){
        GameNumber result = service.findById(id);
        if(result ==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


    //Deletes a game with the specified id
    @DeleteMapping("/app")
    public ResponseEntity delete(@PathVariable int id){
        if(service.deleteById(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
