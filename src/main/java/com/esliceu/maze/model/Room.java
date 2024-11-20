package com.esliceu.maze.model;

public class Room {
    // A north, south, east i west es la id de la porta(clase door), no de la room.
    int id;
    String roomName;
    int north;
    int south;
    int east;
    int west;
    String roomKey;
    boolean coin;
}