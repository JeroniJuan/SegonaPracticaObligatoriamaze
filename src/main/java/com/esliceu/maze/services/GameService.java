package com.esliceu.maze.services;

import com.esliceu.maze.dao.GameDao;
import com.esliceu.maze.dao.GameDaoImplement;
import com.esliceu.maze.model.Door;
import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    GameDao gameDao = new GameDaoImplement();
    public GameMap getMap(int mapid){
        return gameDao.getMap(mapid);
    }

    public Room getRoom(int roomid){
        return gameDao.getRoom(roomid);
    }

    public Door getDoor(int doorid){
        if (doorid == -1){
            return null;
        }
        return gameDao.getDoor(doorid);
    }

    public Game startGame(GameMap map) {
        Game game = new Game();
        game.setCurrentRoomID(map.getId());
        game.setCoinAmount(0);
        return game;
    }
    public Game moveRoom(Game game, String dir){
        if (dir.equals("n")){
            game.setCurrentRoomID(getRoom(game.getCurrentRoomID()).getNorth());
        } else if (dir.equals("e")) {
            game.setCurrentRoomID(getRoom(game.getCurrentRoomID()).getEast());
        }else if (dir.equals("w")){
            game.setCurrentRoomID(getRoom(game.getCurrentRoomID()).getWest());
        } else if (dir.equals("s")) {
            game.setCurrentRoomID(getRoom(game.getCurrentRoomID()).getSouth());
        }
        return game;
    }
    public int getOtherSideRoomID(Door door, int currentRoomID){
        int side1 = door.getSide1();
        int side2 = door.getSide2();
        if (side1 == currentRoomID) {
            return side2;
        } else {
            return side1;
        }
    }
}
