package com.esliceu.maze.model;

public class GameMap {
    int id;
    String mapName;
    int startRoomID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getStartRoomID() {
        return startRoomID;
    }

    public void setStartRoomID(int startRoomID) {
        this.startRoomID = startRoomID;
    }
}
