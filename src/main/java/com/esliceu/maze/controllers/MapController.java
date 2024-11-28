package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.services.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {

    @Autowired
    private GameService gameService;

    @GetMapping("/map")
    public String getMap(Model model, HttpSession session, @RequestParam(required = false) String gameIDStr) {
        Game currentGame;
        GameMap currentMap = new GameMap();
        int gameID = (session.getAttribute("gameID") != null) ? (int) session.getAttribute("gameID") : -1;
        if (gameIDStr != null){
            gameID = Integer.parseInt(gameIDStr);
        }
        String mapid = (String) session.getAttribute("mapid");
        if (mapid != null){
            currentMap = gameService.getMap(Integer.parseInt(mapid));
        }
        if (gameID == -1){
            String gameName = (String) session.getAttribute("gameName");
            session.setAttribute("gameName", null);
            currentGame = gameService.startGame(currentMap, (String) session.getAttribute("user"), gameName);
            session.setAttribute("gameID", currentGame.getId());
            System.out.println("CurrentGameID en map: " + currentGame.getId());
        }else {
            currentGame = gameService.getGame(gameID);
        }

        int currentRoomId = currentGame.getCurrentRoomID();

        // Info de la partida actual.
        System.out.println("-----------------------------------------------");
        System.out.println("Current Room ID:" + currentRoomId);
        System.out.println("Current Game:" + gameService.getRoom(currentRoomId).getRoomName());
        System.out.println("Coin Amount:" + currentGame.getCoinAmount());
        System.out.println("Coins Grabbed:" + currentGame.getCoinsGrabbed());
        System.out.println("Keys:" + currentGame.getKeysGrabbed());
        System.out.println("Opened Doors: " + currentGame.getOpenedDoors());
        System.out.println("-----------------------------------------------");


        model.addAttribute("mapid", currentMap.getId());
        model.addAttribute("currentRoom", currentRoomId);
        model.addAttribute("gameMessage" , session.getAttribute("mapMessage"));
        model.addAttribute("currentRoomName", gameService.getRoom(currentRoomId).getRoomName());
        model.addAttribute("timePassed", currentGame.getTimePassed());
        model.addAttribute("coinAmount", currentGame.getCoinAmount());
        model.addAttribute("keys", currentGame.getKeysGrabbed());
        model.addAttribute("north", gameService.getDoor(gameService.getRoom(currentRoomId).getNorth(), currentGame));
        model.addAttribute("east", gameService.getDoor(gameService.getRoom(currentRoomId).getEast(), currentGame));
        model.addAttribute("south", gameService.getDoor(gameService.getRoom(currentRoomId).getSouth(), currentGame));
        model.addAttribute("west", gameService.getDoor(gameService.getRoom(currentRoomId).getWest(), currentGame));
        if (gameService.canGrabKey(currentGame)){
            model.addAttribute("key", gameService.getRoom(currentRoomId).getRoomKey());
        }else {
            model.addAttribute("key", null);
        }
        if (gameService.canGrabCoin(currentGame)){
            model.addAttribute("coin", gameService.getRoom(currentRoomId).hasCoin());
        }else{
            model.addAttribute("coin", false);
        }

        return "map";
    }
}
