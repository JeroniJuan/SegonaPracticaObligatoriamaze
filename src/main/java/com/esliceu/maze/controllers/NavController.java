package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Door;
import com.esliceu.maze.model.Game;
import com.esliceu.maze.services.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavController {

    @Autowired
    private GameService gameService;

    @PostMapping("/nav")
    public String postNav(@RequestParam String direction,@RequestParam String timePassed, HttpSession session) {
        int gameID = (int) session.getAttribute("gameID");
        Game currentGame = gameService.getGame(gameID);
        if (currentGame == null) {
            return "redirect:/start";
        }
        currentGame.setTimePassed(Integer.parseInt(timePassed));
        int currentRoomId = currentGame.getCurrentRoomID();
        boolean canPass = true;
        int newRoomId = -1;
        Door door = new Door();

        switch (direction) {
            case "N":
                door = gameService.getDoor(gameService.getRoom(currentRoomId).getNorth(), currentGame);
                if (door == null) break;
                if (!door.isOpen()) {
                    canPass = false;
                }
                break;
            case "S":
                door = gameService.getDoor(gameService.getRoom(currentRoomId).getSouth(), currentGame);
                if (door == null) break;
                if (!door.isOpen()) {
                    canPass = false;
                }
                break;
            case "E":
                door = gameService.getDoor(gameService.getRoom(currentRoomId).getEast(), currentGame);
                if (door == null) break;
                if (!door.isOpen()) {
                    canPass = false;
                }
                break;
            case "W":
                door = gameService.getDoor(gameService.getRoom(currentRoomId).getWest(), currentGame);
                if (door == null) break;
                if (!door.isOpen()) {
                    canPass = false;
                }
                break;
        }
        if (canPass && door != null) newRoomId = gameService.getOtherSideRoomID(door, currentRoomId);
        if (newRoomId != -1) {
            session.setAttribute("mapMessage", "");
            currentGame.setCurrentRoomID(newRoomId);
        }else{
            session.setAttribute("mapMessage", "No pots avançar aqui.");
        }
        currentGame.sumMovesAmount();
        gameService.updateGame(currentGame);
        return "redirect:/map";
    }
}
