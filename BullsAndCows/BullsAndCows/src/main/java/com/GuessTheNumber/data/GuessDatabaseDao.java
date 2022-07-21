package com.GuessTheNumber.data;

import com.GuessTheNumber.models.GameNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**THIS IS THE FOR THE SQL DATABASE SIDE SO ALL THE GAME OBJECTS ARE READ AND WRITTEN TO THE DATABASE. */
@Repository
@Profile("database")
public class GuessDatabaseDao implements  GuessDao{

    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public GuessDatabaseDao(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }


    //CRUD METHODS
    @Override
    public GameNumber add(GameNumber game) {
        final String sql = "INSERT INTO game(GameId,GameDate, UserRound, UserGuess, GameStatus) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        String result = Arrays.toString(game.getResults());

        jdbcTemplateObject.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,game.getId());
            statement.setString(2,game.getDate());
            statement.setInt(3,game.getGameRound());
            statement.setString(4,result);
            statement.setBoolean(5,game.isStatus());

            return statement;

        },keyHolder);
        game.setId(keyHolder.getKey().intValue());


        return game;
    }


//RETRIEVES THE CURRENT LIST OF GAMES
    @Override
    public List<GameNumber> getAll() {
        final String sql= "Select GameId,UserRound,UserGuess,GameStatus FROM game;";
        return jdbcTemplateObject.query(sql,new GameMapper());
    }

    //RETRIEVES A GAME WITH THE SPECIFIED ID
    @Override
    public GameNumber findById(int id) {

        final String sql="SELECT GameId, UserRound, UserGuess, GameStatus "+"FROM game WHERE Gameid =?;";

        return jdbcTemplateObject.queryForObject(sql,new GameMapper(),id);
    }

    //UPDATES A CURRENT GAME
    @Override
    public boolean update(GameNumber game) {
        final String sql = "UPDATE game SET "
                +"GameId = ?,"
                +"UserRound = ?, "
                +"UserGuess = ?, "
                +"GameStatus = ?;";

        return jdbcTemplateObject.update(sql,
                game.getGameRound()
                ,game.getStringResults()
                ,game.isStatus()
                ,game.getId())>0;

    }
//DELETES A GAME
    @Override
    public boolean deleteById(int id) {
        final String sql = "Delete FROM Game WHERE GameId = ?;";
        return jdbcTemplateObject.update(sql,id)>0;
    }


    //GAME LOGIC METHODS - MAINLY DEFINED IN THE MEMORY IMPLEMENTATION WHICH IS USED MOREOVER THE TIME
    //SO IT WOULD BE PREFERRED TO TEST THE GAME LOGIC THAT WAY :)
    @Override
    public GameNumber guess(int userGuess, int GameId) {
        return null;
    }

    @Override
    public int beginGame() {
        return 0;
    }

    @Override
    public int[] generateArray() {
        return new int[0];
    }

    @Override
    public String[] compareDifferences(int[] userArray, int[] actualArray) {
        return new String[0];
    }

    @Override
    public String contains(int[] arr, int key) {
        return null;
    }

    //THIS USES A RESULT SET TO BE ABLE TO PROCESS ALL  THE DATA AND MARSHALL IT CORRECTLY TO THE DATABASE
    private static final class GameMapper implements RowMapper<GameNumber>{
        @Override
        public GameNumber mapRow(ResultSet rs, int index) throws SQLException{
            GameNumber gn = new GameNumber();
//            gn.setId(rs.getInt("GameId"));

            gn.setGameRound(rs.getInt("GameDate"));
            gn.setGameRound(rs.getInt("UserRound"));
            gn.setTotalResult(rs.getString("UserGuess"));
            gn.setStatus(rs.getBoolean("GameStatus"));


            return gn;
        }

    }
}
