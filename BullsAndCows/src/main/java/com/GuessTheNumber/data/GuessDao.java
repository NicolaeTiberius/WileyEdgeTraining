package com.GuessTheNumber.data;

import com.GuessTheNumber.models.GameNumber;

import java.util.List;

public interface GuessDao {

    GameNumber add(GameNumber game);

    List<GameNumber> getAll();

    GameNumber findById(int id);

    boolean update(GameNumber game);


}
