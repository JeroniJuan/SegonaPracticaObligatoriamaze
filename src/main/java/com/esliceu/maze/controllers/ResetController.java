package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.services.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResetController {
    @Autowired
    GameService gameService;
    @GetMapping("/reset")
    public String getReset(HttpSession session){
        Game currentGame = gameService.getGame((int) session.getAttribute("gameID"));
        currentGame.resetGame();
        gameService.updateGame(currentGame);
        return "redirect:/map";
    }
}
