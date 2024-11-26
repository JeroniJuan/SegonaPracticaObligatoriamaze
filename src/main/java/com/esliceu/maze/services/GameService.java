package com.esliceu.maze.services;

import com.esliceu.maze.dao.GameDao;
import com.esliceu.maze.dao.GameDaoImplement;
import com.esliceu.maze.model.Door;
import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.model.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    public boolean checkKeys(Door door, Game currentGame) {
        return true;
    }

    public boolean canGrabCoin(Game game) {
        Room room = getRoom(game.getCurrentRoomID());
        Gson gson = new Gson();

        String coinsGrabbedJson = game.getCoinsGrabbed();
        Set<String> coinsGrabbedSet;

        if (coinsGrabbedJson == null || coinsGrabbedJson.isEmpty()) {
            coinsGrabbedSet = new HashSet<>();
        } else {
            coinsGrabbedSet = gson.fromJson(coinsGrabbedJson, new TypeToken<Set<String>>() {}.getType());
        }

        return !coinsGrabbedSet.contains(room.getRoomName());
    }

    public boolean canGrabKey(Game game) {
        Room room = getRoom(game.getCurrentRoomID());
        Gson gson = new Gson();

        String keysJson = game.getKeys();
        Set<String> keysSet;

        if (keysJson == null || keysJson.isEmpty()) {
            keysSet = new HashSet<>();
        } else {
            keysSet = gson.fromJson(keysJson, new TypeToken<Set<String>>() {}.getType());
        }

        return !keysSet.contains(room.getRoomKey());
    }

    public boolean hasDoorKey(Game game, Door door) {
        String keysJson = game.getKeys();
        Gson gson = new Gson();
        Set<String> keysSet;

        if (keysJson == null || keysJson.isEmpty()) {
            keysSet = new HashSet<>();
        } else {
            keysSet = gson.fromJson(keysJson, new TypeToken<Set<String>>() {}.getType());
        }

        String doorKey = door.getDoorKey();

        return keysSet.contains(doorKey);
    }


}
