package com.esliceu.maze.services;

import com.esliceu.maze.dao.UserDao;
import com.esliceu.maze.dao.UserDaoImplement;
import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.User;
import com.esliceu.maze.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserDao userDao = new UserDaoImplement();
    public boolean checkUser(String username, String password, Model model) {
        User user = userDao.findByUsername(username);
        if (user != null){
            if (!Utils.toSHA256(password).equals(user.getPassword())){
                model.addAttribute("loginStatus", "password incorrecta");
            }else {
                model.addAttribute("loginStatus", null);
            }
            return Utils.toSHA256(password).equals(user.getPassword());
        }
        return false;
    }
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
            if (Objects.equals(usernames.get(i), user.getUsername())) return false;
        }
        user.setPassword(Utils.toSHA256(user.getPassword()));   // Encripta en hash la password
        userDao.save(user);
        return true;
    }

    public User findByUsername(String user) {
        return userDao.findByUsername(user);
    }

}
