package com.esliceu.maze.services;

import com.esliceu.maze.dao.UserDao;
import com.esliceu.maze.dao.UserDaoImplement;
import com.esliceu.maze.model.User;
import com.esliceu.maze.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginService {
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
}
