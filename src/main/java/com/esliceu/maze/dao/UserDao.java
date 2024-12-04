package com.esliceu.maze.dao;

import com.esliceu.maze.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);
    void saveUser(User u);
    List<String> getAllUsernames();
}
