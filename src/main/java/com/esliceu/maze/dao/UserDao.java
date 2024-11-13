package com.esliceu.maze.dao;

import com.esliceu.maze.model.User;

public interface UserDao {
    User findById(int id);
    void save(User u);
}
