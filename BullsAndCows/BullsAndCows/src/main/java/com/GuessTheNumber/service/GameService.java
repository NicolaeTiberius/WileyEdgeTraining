package com.GuessTheNumber.service;

import com.GuessTheNumber.data.GuessDao;
import com.GuessTheNumber.models.GameNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GameService{

    private GuessDao data;

    @Autowired
    public GameService(GuessDao data){this.data = data;}

    public GameNumber add(GameNumber game){ return data.add(game);}

    public int beginGame(){return data.beginGame();}

    public int[] generateArray(){return data.generateArray();}

    public String[] compareDifferences(int[] userArray, int[]actualArray){return data.compareDifferences(userArray,actualArray);}

    public List<GameNumber> getAll(){return data.getAll();}

    public GameNumber findById(int id){return data.findById(id);}

    public boolean update(GameNumber game){return data.update(game);}

    public boolean deleteById(int id){return data.deleteById(id);}

    public GameNumber guess(int userGuess, int GameId){return data.guess(userGuess,GameId);}



}
