package com.esliceu.maze.dao;

import com.esliceu.maze.model.*;

import java.util.List;

public interface GameDao {
    GameMap getMap(int mapid);

    Room getRoom(int roomid);

    Door getDoor(int doorid);
    void insertGame(Game game);
    Game getGame(int id);

    Game getLatestGame();

    void updateGame(Game game);

    List<Game> getGamesByUserId(int userId);

    void deleteGame(Game currentGame);

    void insertRecord(Records gameRecords);

    List<Records> getRecordsByUserId(int userID);

    List<Records> getTop5RecordsWithLeastMovements();

    List<Records> getTop5RecordsWithLeastTime();
}
