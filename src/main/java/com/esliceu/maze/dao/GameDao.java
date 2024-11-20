package com.esliceu.maze.dao;

import com.esliceu.maze.model.Door;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.model.Room;

public interface GameDao {
    GameMap getMap(int mapid);

    Room getRoom(int roomid);

    Door getDoor(int doorid);
}
