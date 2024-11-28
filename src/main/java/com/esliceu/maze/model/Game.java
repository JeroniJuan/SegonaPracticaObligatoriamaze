package com.esliceu.maze.model;

import com.esliceu.maze.services.GameService;

public class Game {
    int id;
    int userID;
    int mapID;
    String gameName;
    int currentRoomID;
    int coinAmount = 0;
    String coinsGrabbed; // Llista de les habitacions que tenian moneda que s'han agafat.
    String keysGrabbed;  // Llista del nom de les claus que s'han agafat.
    String openedDoors; // Llista de portes obertes.
    int movesAmount = 0;
    int timePassed = 0; // Temps que ha passat en la partida en segons.

    public void resetGame(GameMap map) {
        this.currentRoomID = map.startRoomID;
        coinAmount = 0;
        coinsGrabbed = null;
        keysGrabbed = null;
        openedDoors = null;
        movesAmount = 0;
        timePassed = 0;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentRoomID() {
        return currentRoomID;
    }

    public void setCurrentRoomID(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getCoinsGrabbed() {
        return coinsGrabbed;
    }

    public void setCoinsGrabbed(String coinsGrabbed) {
        this.coinsGrabbed = coinsGrabbed;
    }

    public String getKeysGrabbed() {
        return keysGrabbed;
    }

    public void setKeysGrabbed(String keysGrabbed) {
        this.keysGrabbed = keysGrabbed;
    }

    public String getOpenedDoors() {
        return openedDoors;
    }

    public void setOpenedDoors(String openedDoors) {
        this.openedDoors = openedDoors;
    }

    public int getMovesAmount() {
        return movesAmount;
    }

    public void setMovesAmount(int movesAmount) {
        this.movesAmount = movesAmount;
    }

    public void sumMovesAmount(){
        movesAmount++;
    }

    public void sumCoin() {
        coinAmount++;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(int timePassed){
        this.timePassed = timePassed;
    }


}
