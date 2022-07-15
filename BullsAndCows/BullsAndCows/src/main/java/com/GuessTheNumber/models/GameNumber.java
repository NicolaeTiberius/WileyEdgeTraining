package com.GuessTheNumber.models;

import java.util.Arrays;

public class GameNumber {


    //VARIABLES NEEDED
    private int id;
    private static int count = 1;

    private int gameRound=0;
    private int[] randomNumber;

    private boolean status;

    private String[]results;


    //CONSTRUCTOR
    public GameNumber(boolean status) {

        this.status = status;
        setId(count++);
    }


    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String[] getResults() {
        return results;
    }

    public void setResults(String[] results) {
        this.results = results;
    }

    public int[] getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int[] randomNumber) {
        this.randomNumber = randomNumber;
    }

    public int getGameRound() {
        return gameRound;
    }

    public void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    @Override
    public String toString() {
        return "GameNumber{" +
                "id=" + id +
                ", gameRound=" + gameRound +
                ", randomNumber=" + Arrays.toString(randomNumber) +
                ", status=" + status +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
