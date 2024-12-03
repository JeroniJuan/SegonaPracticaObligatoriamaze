package com.esliceu.maze.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String getLogout(HttpSession session){
        session.setAttribute("user", null);
        session.setAttribute("gameID", null);
        session.setAttribute("currentGame", null);
        session.setAttribute("mapid", null);
        return "redirect:/login";
    }
}
