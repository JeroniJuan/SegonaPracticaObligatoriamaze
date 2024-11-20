package com.esliceu.maze.services;

import com.esliceu.maze.dao.UserDao;
import com.esliceu.maze.dao.UserDaoImplement;
import com.esliceu.maze.model.User;
import com.esliceu.maze.utils.Utils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    UserDao userDao = new UserDaoImplement();
    public boolean RegisterUser(User user, Model model){
        List<String> usernames = userDao.getAllUsernames();
        if (user.getUsername().length() < 2 || user.getPassword().length() < 4){
            if (user.getUsername().length() < 2) model.addAttribute("registerMessage","Usuari invalid. El usuari ha de ser de minim 3 lletres");
            if (user.getPassword().length() < 4) model.addAttribute("registerMessage2","Password invalida. La contrasenya ha de ser de minim 5 lletres");
            return false;
        } else if (user.getUsername().length() > 15 || user.getPassword().length() > 15) {
            if (user.getUsername().length() > 15) model.addAttribute("registerMessage","Usuari invalid. El usuari ha de ser de maxim 15 lletres");
            if (user.getPassword().length() > 15) model.addAttribute("registerMessage2","Password invalida. La contrasenya ha de ser de maxim 15 lletres");
            return false;
        }
        model.addAttribute("registerMessage","");
        model.addAttribute("registerMessage2","");

        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i) == user.getUsername()) return false;
        }
        user.setPassword(Utils.toSHA256(user.getPassword()));   // Encripta en hash la password
        userDao.save(user);
        return true;
    }
}
