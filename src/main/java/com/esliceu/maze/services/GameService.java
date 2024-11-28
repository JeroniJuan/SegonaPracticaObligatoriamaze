package com.esliceu.maze.services;

import com.esliceu.maze.dao.GameDao;
import com.esliceu.maze.dao.GameDaoImplement;
import com.esliceu.maze.dao.UserDao;
import com.esliceu.maze.dao.UserDaoImplement;
import com.esliceu.maze.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {
    @Autowired
    GameDao gameDao = new GameDaoImplement();

    @Autowired
    UserService userService = new UserService();

    public GameMap getMap(int mapid) {
        return gameDao.getMap(mapid);
    }

    public Room getRoom(int roomid) {
        return gameDao.getRoom(roomid);
    }

    public Door getDoor(int doorId, Game game) {
        if (doorId == -1) {
            return null;
        }

        Door door = gameDao.getDoor(doorId);
        if (door != null) {
            String openedDoorsJson = game.getOpenedDoors();
            if (openedDoorsJson != null && !openedDoorsJson.isEmpty()) {
                Gson gson = new Gson();
                Set<String> openedDoorsSet = gson.fromJson(openedDoorsJson, new TypeToken<Set<String>>() {
                }.getType());
                if (openedDoorsSet.contains(String.valueOf(doorId))) {
                    door.setOpen(true);
                }
            }
        }
        return door;
    }


    public Game startGame(GameMap map, String user, String gameName) {
        Game game = new Game();
        game.setGameName(gameName);
        game.setCurrentRoomID(map.getId());
        game.setMapID(map.getId());
        game.setCoinAmount(0);
        game.setMovesAmount(0);
        game.setTimePassed(0);
        game.setUserID(userService.findByUsername(user).getId());
        game.setCoinsGrabbed("");
        game.setKeysGrabbed("");
        game.setOpenedDoors("");
        gameDao.insertGame(game);
        return gameDao.getLatestGame();
    }

    public int getOtherSideRoomID(Door door, int currentRoomID) {
        int side1 = door.getSide1();
        int side2 = door.getSide2();
        if (side1 == currentRoomID) {
            return side2;
        } else {
            return side1;
        }
    }

    public Door getDoorFromDirection(String dir, Door door, int currentRoomID, Game currentGame) {
        if (dir.equals("n")) {
            door = getDoor(getRoom(currentRoomID).getNorth(), currentGame);
        } else if (dir.equals("e")) {
            door = getDoor(getRoom(currentRoomID).getEast(), currentGame);
        } else if (dir.equals("s")) {
            door = getDoor(getRoom(currentRoomID).getSouth(), currentGame);
        } else if (dir.equals("w")) {
            door = getDoor(getRoom(currentRoomID).getWest(), currentGame);
        }
        return door;
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
            coinsGrabbedSet = gson.fromJson(coinsGrabbedJson, new TypeToken<Set<String>>() {
            }.getType());
        }

        return !coinsGrabbedSet.contains(room.getRoomName());
    }

    public boolean canGrabKey(Game game) {
        Room room = getRoom(game.getCurrentRoomID());
        Gson gson = new Gson();

        String keysJson = game.getKeysGrabbed();
        Set<String> keysSet;

        if (keysJson == null || keysJson.isEmpty()) {
            keysSet = new HashSet<>();
        } else {
            keysSet = gson.fromJson(keysJson, new TypeToken<Set<String>>() {
            }.getType());
        }

        return !keysSet.contains(room.getRoomKey());
    }

    public boolean hasDoorKey(Game game, Door door) {
        String keysJson = game.getKeysGrabbed();
        Gson gson = new Gson();
        Set<String> keysSet;

        if (keysJson == null || keysJson.isEmpty()) {
            keysSet = new HashSet<>();
        } else {
            keysSet = gson.fromJson(keysJson, new TypeToken<Set<String>>() {
            }.getType());
        }

        String doorKey = door.getDoorKey();

        return keysSet.contains(doorKey);
    }


    public boolean GameisFinished(Game currentGame) {
        if (currentGame.getCurrentRoomID() == 7) {
            return true;
        }
        return false;
    }

    public Game getGame(int id){
        return gameDao.getGame(id);
    }


    public void updateGame(Game currentGame) {
        gameDao.updateGame(currentGame);
    }

    public List<Game> getGamesByUserId(int userid) {
        return gameDao.getGamesByUserId(userid);
    }


}
