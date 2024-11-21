package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.services.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @Autowired
    private GameService gameService;

    @GetMapping("/map")
    public String getMap(Model model, HttpSession session) {
        Game currentGame = (Game) session.getAttribute("currentGame");
        GameMap currentMap = gameService.getMap(Integer.parseInt((String) session.getAttribute("mapid")));
        if (currentGame == null) {
            currentGame = gameService.startGame(currentMap);
            session.setAttribute("currentGame", currentGame);
        }


        int currentRoomId = currentGame.getCurrentRoomID();
        System.out.println("Current Room ID:" + currentRoomId);
        System.out.println("Current Game:" + currentGame.getCurrentRoomID() + " " + currentGame.getCoinAmount());

        model.addAttribute("mapid", currentMap.getId());
        model.addAttribute("currentRoom", currentGame.getCurrentRoomID());
        model.addAttribute("north", gameService.getDoor(gameService.getRoom(currentRoomId).getNorth()));
        model.addAttribute("east", gameService.getDoor(gameService.getRoom(currentRoomId).getEast()));
        model.addAttribute("south", gameService.getDoor(gameService.getRoom(currentRoomId).getSouth()));
        model.addAttribute("west", gameService.getDoor(gameService.getRoom(currentRoomId).getWest()));

        return "map";
    }
}
