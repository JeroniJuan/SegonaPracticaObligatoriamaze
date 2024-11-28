package com.esliceu.maze.services;

import com.esliceu.maze.dao.GameDao;
import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {
    @Autowired
    GameDao gameDao;
    public List<Records> getTop5RecordsWithLeastMovements(){
        return gameDao.getTop5RecordsWithLeastMovements();
    }
    public List<Records> getTop5RecordsWithLeastTime(){
        return gameDao.getTop5RecordsWithLeastTime();
    }
    public void endGame(Game currentGame, String playerName) {
        Records gameRecords = new Records();
        gameRecords.setUserID(currentGame.getUserID());
        gameRecords.setGameName(currentGame.getGameName());
        gameRecords.setPlayerName(playerName);
        gameRecords.setMapID(currentGame.getMapID());
        gameRecords.setMovements(currentGame.getMovesAmount());
        gameRecords.setTime(currentGame.getTimePassed());
        gameDao.deleteGame(currentGame);
        gameDao.insertRecord(gameRecords);
    }

    public List<Records> getRecordsByUserId(int userID) {
        return gameDao.getRecordsByUserId(userID);
    }
}
