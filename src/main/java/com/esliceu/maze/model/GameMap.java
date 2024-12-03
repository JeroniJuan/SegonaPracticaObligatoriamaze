package com.esliceu.maze.model;

public class GameMap {
    int id;
    String mapName;
    int startRoomId;


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

    public int getStartRoomId() {
        return startRoomId;
    }

    public void setStartRoomId(int startRoomId) {
        this.startRoomId = startRoomId;
    }
}
