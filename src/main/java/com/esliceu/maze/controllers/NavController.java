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
    public String getNav(@RequestParam String direction, HttpSession session) {
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame != null) {
            int currentRoomId = currentGame.getCurrentRoomID();

            int newRoomId = -1;
            Door door;
            switch (direction) {
                case "N":
                    door = gameService.getDoor(gameService.getRoom(currentRoomId).getNorth());
                    newRoomId = gameService.getOtherSideRoomID(door, currentRoomId);
                    break;
                case "S":
                    door = gameService.getDoor(gameService.getRoom(currentRoomId).getSouth());
                    newRoomId = gameService.getOtherSideRoomID(door, currentRoomId);
                    break;
                case "E":
                    door = gameService.getDoor(gameService.getRoom(currentRoomId).getEast());
                    newRoomId = gameService.getOtherSideRoomID(door, currentRoomId);
                    break;
                case "W":
                    door = gameService.getDoor(gameService.getRoom(currentRoomId).getWest());
                    newRoomId = gameService.getOtherSideRoomID(door, currentRoomId);
                    break;
            }

            if (newRoomId != -1) {
                currentGame.setCurrentRoomID(newRoomId);
            }
        }

        session.setAttribute("currentGame", currentGame);
        return "redirect:/map";
    }
}
