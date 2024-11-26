package com.esliceu.maze.model;

public class Game {
    int currentRoomID;
    int coinAmount = 0;
    String coinsGrabbed; // Llista de les habitacions que tenian moneda que
                                                    // s'han agafat.
    String keys;  // Llista del nom de les claus que s'han agafat
    String openedDoors;

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

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getOpenedDoors() {
        return openedDoors;
    }

    public void setOpenedDoors(String openedDoors) {
        this.openedDoors = openedDoors;
    }

    public void sumCoin() {
        coinAmount++;
    }
}
