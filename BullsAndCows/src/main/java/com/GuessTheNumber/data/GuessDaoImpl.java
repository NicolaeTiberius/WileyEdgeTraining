package com.GuessTheNumber.data;

import com.GuessTheNumber.models.GameNumber;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GuessDaoImpl implements  GuessDao {

    //Used to read and write to the database

    private static final List<GameNumber> gameNumbers = new ArrayList<>();

    @Override
    public GameNumber add(GameNumber game) {
        int nextId = gameNumbers.stream().mapToInt(i->i.getId())
                .max()
                .orElse(0)+1;

        game.setId(nextId);
        gameNumbers.add(game);
        return game;
    }

    @Override
    public List<GameNumber> getAll() {
        return new ArrayList<>(gameNumbers);
    }

    @Override
    public GameNumber findById(int id) {
        return gameNumbers.stream()
                .filter(i->i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(GameNumber game) {
        int index = 0;
        while(index <gameNumbers.size()
            && gameNumbers.get(index).getId()!=game.getId()){
                index++;
        }
        if (index< gameNumbers.size()){
            gameNumbers.set(index,game);
        }
        return index < gameNumbers.size();
    }

}
