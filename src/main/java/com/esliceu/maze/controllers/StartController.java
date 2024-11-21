package com.esliceu.maze.controllers;

import com.esliceu.maze.filters.LoginInterceptor;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {
    @Autowired
    LoginInterceptor loginInterceptor;

    @GetMapping("/start")
    public String getStart() {
        return "start";
    }

    @PostMapping("/start")
    public String postStart(@RequestParam String mapid, HttpSession session) {
        System.out.println("Mapid selected: " + mapid);
        if (mapid.equals("1") || mapid.equals("2")) {
            session.setAttribute("mapid", mapid);
            return "redirect:/map";
        }
        return "start";
    }
}

