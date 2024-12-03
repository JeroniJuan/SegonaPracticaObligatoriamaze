package com.esliceu.maze.dao;

import com.esliceu.maze.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDaoImplement implements GameDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public GameMap getMap(int mapid) {
        return jdbcTemplate.queryForObject("select * from gameMap where id=?",
                new BeanPropertyRowMapper<>(GameMap.class)
                ,mapid
        );
    }

    @Override
    public Room getRoom(int roomid) {
        if (roomid == -1) return null;
        return jdbcTemplate.queryForObject("select * from room where id=?",
                new BeanPropertyRowMapper<>(Room.class)
                ,roomid
        );
    }

    @Override
    public Door getDoor(int doorid) {
        return jdbcTemplate.queryForObject("select * from door where id=?",
                new BeanPropertyRowMapper<>(Door.class)
                ,doorid
        );
    }

    @Override
    public void insertGame(Game game) {
        String sql = "INSERT INTO game (id, userID, mapID, gameName, currentRoomID, coinAmount, coinsGrabbed, keysGrabbed, openedDoors, movesAmount, timePassed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                game.getId(),
                game.getUserID(),
                game.getMapID(),
                game.getGameName(),
                game.getCurrentRoomID(),
                game.getCoinAmount(),
                game.getCoinsGrabbed(),
                game.getKeysGrabbed(),
                game.getOpenedDoors(),
                game.getMovesAmount(),
                game.getTimePassed()
        );
    }

    @Override
    public Game getGame(int id) {
        String sql = "SELECT * FROM game WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Game.class), id);
    }

    @Override
    public Game getLatestGame() {
        String query = "SELECT * FROM game ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Game.class));
    }

    @Override
    public void updateGame(Game game) {
        String query = """
        UPDATE game 
        SET currentRoomID = ?, 
            coinAmount = ?, 
            coinsGrabbed = ?, 
            keysGrabbed = ?, 
            openedDoors = ?, 
            movesAmount = ?, 
            timePassed = ?
        WHERE id = ?
    """;

        jdbcTemplate.update(
                query,
                game.getCurrentRoomID(),
                game.getCoinAmount(),
                game.getCoinsGrabbed(),
                game.getKeysGrabbed(),
                game.getOpenedDoors(),
                game.getMovesAmount(),
                game.getTimePassed(),
                game.getId()
        );
    }

    @Override
    public List<Game> getGamesByUserId(int userId) {
        String sql = "SELECT * FROM game WHERE userID = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Game.class), userId);
    }

    @Override
    public void deleteGame(Game game) {
        jdbcTemplate.update("DELETE FROM game WHERE id = ?", game.getId());
    }

    @Override
    public void insertRecord(Records record) {
        String sql = "INSERT INTO records (userID, gameName, playerName, mapID, movements, time) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, record.getUserID(), record.getGameName(), record.getPlayerName(),
                record.getMapID(), record.getMovements(), record.getTime());
    }

    @Override
    public List<Records> getRecordsByUserId(int userID) {
        String sql = "SELECT * FROM records WHERE userID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Records.class), userID);
    }

    @Override
    public List<Records> getTop5RecordsWithLeastMovements() {
        String sql = "SELECT * FROM records ORDER BY movements ASC LIMIT 5";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Records.class));
    }

    @Override
    public List<Records> getTop5RecordsWithLeastTime() {
        String sql = "SELECT * FROM records ORDER BY time ASC LIMIT 5";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Records.class));
    }
}
