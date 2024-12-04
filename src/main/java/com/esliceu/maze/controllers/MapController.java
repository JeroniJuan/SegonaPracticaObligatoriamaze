package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.services.GameService;
import com.esliceu.maze.services.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/map")
    public String getMap(Model model, HttpSession session) {
        Game currentGame;
        int gameID = (session.getAttribute("gameID") != null) ? (int) session.getAttribute("gameID") : -1;
        GameMap currentMap = new GameMap();
        String mapid = null;
        if (gameID != -1){
            session.setAttribute("mapid", null);
        }else{
            mapid = (String) session.getAttribute("mapid");
        }
        if (mapid != null){
            currentMap = gameService.getMap(Integer.parseInt(mapid));
        }
        if (gameID == -1){
            if (mapid == null){
                return "redirect:/start";
            }
            String gameName = (String) session.getAttribute("gameName");
            session.setAttribute("gameName", null);
            currentGame = gameService.startGame(currentMap, (String) session.getAttribute("user"), gameName);
            session.setAttribute("gameID", currentGame.getId());
        }else {
            currentGame = gameService.getGame(gameID);
            if (currentGame.getUserID() != userService.findByUsername((String) session.getAttribute("user")).getId()){
                return "redirect:/start"; // Si un usuari esta intentant iniciar una partida que no es seva, no es permet.
            }
        }
        int currentRoomId = currentGame.getCurrentRoomID();

        gameService.addAttributesToModel(model, session, currentGame, currentRoomId);
        return "map";
    }

}
