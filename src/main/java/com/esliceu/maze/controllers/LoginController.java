package com.esliceu.maze.controllers;

import com.esliceu.maze.model.User;
import com.esliceu.maze.services.LoginService;
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
    LoginService loginService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session){
        if(loginService.checkUser(username, password, model)){
            session.setAttribute("user", username);
            model.addAttribute("loginStatus", "Currently logged in: " + username);
            return "redirect:/start";
        };
        return "login";
    }
}