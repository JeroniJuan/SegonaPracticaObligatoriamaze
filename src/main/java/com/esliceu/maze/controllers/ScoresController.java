package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.services.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ScoresController {
    @Autowired
    GameService gameService;
    @GetMapping("/scores")
    public String getScores(HttpSession session){
        return "scores";
    }
}
