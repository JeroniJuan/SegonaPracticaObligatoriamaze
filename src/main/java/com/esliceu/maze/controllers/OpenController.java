package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.Door;
import com.esliceu.maze.services.GameService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class OpenController {

    @Autowired
    private GameService gameService;

    @PostMapping("/open")
    public String postOpen(@RequestParam String dir, HttpSession session) {
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame == null) {
            return "redirect:/start";
        }

        // Obtén la sala actual
        int currentRoomID = currentGame.getCurrentRoomID();
        Door door = null;

        // Determinar la puerta de acuerdo a la dirección
        if (dir.equals("n")) {
            door = gameService.getDoor(gameService.getRoom(currentRoomID).getNorth());
        } else if (dir.equals("e")) {
            door = gameService.getDoor(gameService.getRoom(currentRoomID).getEast());
        } else if (dir.equals("s")) {
            door = gameService.getDoor(gameService.getRoom(currentRoomID).getSouth());
        } else if (dir.equals("w")) {
            door = gameService.getDoor(gameService.getRoom(currentRoomID).getWest());
        }

        if (door == null) {
            return "redirect:/map";
        }

        if (gameService.hasDoorKey(currentGame, door)) {
            door.setOpen(true);

            String openedDoorsJson = currentGame.getOpenedDoors();
            Set<String> openedDoorsSet = new HashSet<>();
            if (openedDoorsJson != null && !openedDoorsJson.isEmpty()) {
                Gson gson = new Gson();
                openedDoorsSet = gson.fromJson(openedDoorsJson, new TypeToken<Set<String>>() {}.getType());
            }

            openedDoorsSet.add(String.valueOf(door.getId()));
            currentGame.setOpenedDoors(new Gson().toJson(openedDoorsSet));

            session.setAttribute("currentGame", currentGame);
        }

        return "redirect:/map";
    }
}
