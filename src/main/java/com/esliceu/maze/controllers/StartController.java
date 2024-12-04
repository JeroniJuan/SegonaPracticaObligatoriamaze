package com.esliceu.maze.controllers;

import com.esliceu.maze.dao.GameDao;
import com.esliceu.maze.filters.LoginInterceptor;
import com.esliceu.maze.model.Game;
import com.esliceu.maze.services.GameService;
import com.esliceu.maze.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StartController {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @GetMapping("/start")
    public String getStart(HttpSession session, Model model) {
        String user = (String) session.getAttribute("user");
        List<Game> games = gameService.getGamesByUserId(userService.findByUsername(user).getId());
        model.addAttribute("games", games);
        return "start";
    }

    @PostMapping("/start")
    public String postStart(@RequestParam(required = false) String mapid,
                            @RequestParam(required = false) String gameIDStr,
                            @RequestParam(required = false) String gameNameStr,
                            HttpSession session) {
        session.setAttribute("gameID", null);
        if (mapid != null && !gameNameStr.isEmpty()){
            session.setAttribute("mapid", mapid);
            session.setAttribute("gameName", gameNameStr);
            return "redirect:/map";
        }else if (gameNameStr != null && gameIDStr != null) {
            session.setAttribute("gameID", Integer.parseInt(gameIDStr));
            session.setAttribute("gameName", null);
            return "redirect:/map";
        }
        return "start";
    }
}

