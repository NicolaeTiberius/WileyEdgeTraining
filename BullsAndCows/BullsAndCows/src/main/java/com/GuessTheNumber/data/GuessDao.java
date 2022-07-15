package com.GuessTheNumber.data;

import com.GuessTheNumber.models.GameNumber;

import java.util.List;

public interface GuessDao {


    /**Methods for Making the Game  */
    GameNumber add(GameNumber game);


    int beginGame();

    int[] generateArray();

    String[] compareDifferences(int[]userArray, int[]actualArray);

    String contains(int[]arr,int key);


    /**Methods for Database logic  */
    List<GameNumber> getAll();


    GameNumber findById(int id);

    boolean update(GameNumber game);

    boolean deleteById(int id);

    GameNumber guess(int userGuess,int GameId);


}
