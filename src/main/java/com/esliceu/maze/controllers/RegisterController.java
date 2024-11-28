package com.esliceu.maze.controllers;

import com.esliceu.maze.model.User;
import com.esliceu.maze.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userService.RegisterUser(user, model)){
            return "redirect:/login";
        }
        return "register";
    }
}
