package com.GuessTheNumber.data;

import com.GuessTheNumber.models.GameNumber;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuessDaoImpl implements  GuessDao {


    private static final List<GameNumber> gameNumbers = new ArrayList<>();


    //Used manipulate the arraylist and game logic
    @Override
    public GameNumber add(GameNumber game) {
        gameNumbers.add(game);
        return game;
    }


    @Override
    public int beginGame() {
        GuessDao guessDao = new GuessDaoImpl();
        //starts a game and sets the game status.
        boolean gameStatus = false;
        GameNumber newGame = new GameNumber(gameStatus);
        //adds the game to the arraylist of games and gives it an Id as well.
        guessDao.add(newGame);
        //Generate a random answer to use.
        int[] actualArray = guessDao.generateArray();
        //gives the game a random number.
        newGame.setRandomNumber(actualArray);
        //returns the id of the game created.
        return newGame.getId();
    }

    @Override
    public int[] generateArray() {
        int x[] = {0, 0, 0, 0};
        boolean isDuplicate = false;
        for (int i = 0; i < x.length; i++) {
            int y = (int) (Math.random() * 9) + 1;
            for (int j = 0; j < x.length; j++) {
                if (x[j] == y) {
                    isDuplicate = true; // Flag used to denote duplicate
                    break;
                }
            }
            if (isDuplicate) {
                i--;
                isDuplicate = false;
            } else {
                x[i] = y;
            }
        }
        return x;
    }

    @Override
    public String[] compareDifferences(int[] userArray, int[] actualArray) {
        String result[] = new String[actualArray.length];
        String val = "";
        if (Arrays.equals(userArray, actualArray)) {
            result[0] = "Bull";
            result[1] = "Bull";
            result[2] = "Bull";
            result[3] = "Bull";
            return result;
        } else {
            //loop through the array of the actual array length to check with
            for (int i = 0; i < actualArray.length; i++) {
                String cow = "Cow";
                val = contains(actualArray, userArray[i]); // error here.
              //each integer from the user array and actual array
                if (userArray[i] == actualArray[i]) {
                    result[i] = "Bull";
                } else if (cow.equals(val)) {
                    result[i] = val;
                } else {
                    result[i] = "-";
                }
            }
        }
        return result;
    }

    @Override
    public String contains(int[] arr, int key) {
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                result = "Cow";
            }
        }
        return result;
    }

    @Override
    public List<GameNumber> getAll() {
        return new ArrayList<>(gameNumbers);
    }

    @Override
    public GameNumber findById(int id) {
        return gameNumbers.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(GameNumber game) {
        int index = 0;
        while (index < gameNumbers.size()
                && gameNumbers.get(index).getId() != game.getId()) {
            index++;
        }
        if (index < gameNumbers.size()) {
            gameNumbers.set(index, game);
        }
        return index < gameNumbers.size();
    }

    @Override
    public boolean deleteById(int id) {
        return gameNumbers.removeIf(i -> i.getId() == id);
    }

    @Override
    public String[] guess(int userGuess, int GameId) {
        GuessDao guessDao = new GuessDaoImpl();
        //"guess" – POST – Makes a guess by passing the guess and gameId in as JSON.
        String[] result = new String[4];
        String[] unknownId = new String[1];
        String userArrayString = Integer.toString(userGuess);
        //makes an array that has the same length of the user string.
        int[] userArray = new int[userArrayString.length()];
        //Convert the string array to an actual int array
        for (int i = 0; i <userArrayString.length() ; i++) {
            userArray[i] = userArrayString.charAt(i)-'0';
        }
        int rounds = 0;
        GameNumber userObject = new GameNumber(false);
        //find the id of the game to check it exists
        for (GameNumber game: gameNumbers) {
            int[] actualArray;
            if (game.getId() == GameId) {
                //initialize a game object that will be used to retrieve the status, id and contents.
                //gets the random number of that Game id.
                actualArray = game.getRandomNumber();
                userObject.setId(GameId);
                rounds = userObject.getGameRound();

            } else  {
                unknownId[0] = "Game ID does not exist";
                return unknownId;
            }


            // The program must calculate the results of the guess by comparing
            result = guessDao.compareDifferences(userArray, actualArray);
            String[] winner = {"Bull", "Bull", "Bull", "Bull"};
            //check if the user guess was correct.
            if (Arrays.equals(result, winner)) {
                //sets the user result and status so they finished game.
                userObject.setResults(result);
                userObject.setStatus(true);
                return winner;
            }
            //if the user hasn't guessed correctly yet.
            rounds++;
            userObject.setGameRound(rounds);
        }

        // and mark the game finished if the guess is correct.
        // It returns the Round object with the results filled in.
        return result;
    }
}


