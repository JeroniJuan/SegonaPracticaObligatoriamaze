package com.esliceu.maze.controllers;

import com.esliceu.maze.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@RequestParam(required = false) String username, @RequestParam(required = false) String password, Model model, HttpSession session){
        if (username.isEmpty() || password.isEmpty()) return "login";
        if(userService.checkUser(username, password, model)){
            session.setAttribute("user", username);
            return "redirect:/start";
        };
        return "login";
    }

    @GetMapping("/")
    public String getIndex(){
        return "redirect:/login";
    }
}