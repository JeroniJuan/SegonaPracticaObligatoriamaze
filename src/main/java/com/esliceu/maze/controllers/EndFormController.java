package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.services.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EndFormController {
    @Autowired
    GameService gameService;
    @PostMapping("/endform")
    public String EndFormPost(HttpSession session){
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (gameService.GameisFinished(currentGame)){
            gameService.endGame(currentGame);
            session.setAttribute("currentGame", null);
            session.setAttribute("mapid", null);
            return "redirect:/scores";
        };

        return "redirect:/map";
    }
}
