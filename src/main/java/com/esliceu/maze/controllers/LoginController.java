package com.esliceu.maze.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        return "login";
    }
}
