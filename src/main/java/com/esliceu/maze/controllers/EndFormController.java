package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.services.GameService;
import com.esliceu.maze.services.RecordsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EndFormController {
    @Autowired
    GameService gameService;
    @Autowired
    RecordsService recordsService;
    @PostMapping("/endForm")
    public String EndFormPost(HttpSession session, @RequestParam String timePassed, @RequestParam String playerName){
        int gameID = (int) session.getAttribute("gameID");
        Game currentGame = gameService.getGame(gameID);
        if (gameService.GameisFinished(currentGame)){
            currentGame.setTimePassed(Integer.parseInt(timePassed));
            recordsService.endGame(currentGame, playerName);
            session.setAttribute("gameID", null);
            session.setAttribute("mapid", null);
            return "redirect:/scores";
        };

        return "redirect:/map";
    }
}
