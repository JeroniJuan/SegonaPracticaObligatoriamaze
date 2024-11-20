package com.esliceu.maze.services;

import com.esliceu.maze.dao.GameDao;
import com.esliceu.maze.dao.GameDaoImplement;
import com.esliceu.maze.model.Door;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    GameDao gameDao = new GameDaoImplement();
    public GameMap startMap(int mapid){
        return gameDao.getMap(mapid);
    }

    public Room getRoom(int roomid){
        return gameDao.getRoom(roomid);
    }

    public Door getDoor(int doorid){
        return gameDao.getDoor(doorid);
    }
}
