package com.esliceu.maze.dao;

import com.esliceu.maze.model.User;

import java.util.List;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);
    void save(User u);
    List<String> getAllUsernames();
}
