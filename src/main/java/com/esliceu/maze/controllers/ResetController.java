package com.esliceu.maze.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResetController {
    @GetMapping("/reset")
    public String getReset(HttpSession session){
        session.setAttribute("currentGame", null);
        return "redirect:/map";
    }
}
