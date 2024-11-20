package com.esliceu.maze.controllers;

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
    GameService mapService;
    @GetMapping("/map")
    public String getMap(Model model, HttpSession session){
        int mapid = Integer.parseInt((String) session.getAttribute("mapid"));
        GameMap game = mapService.startMap(mapid);
        return "map";
    }
}
