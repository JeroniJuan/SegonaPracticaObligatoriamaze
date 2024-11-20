package com.esliceu.maze.dao;

import com.esliceu.maze.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImplement implements UserDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User findById(int id) {
        return jdbcTemplate.queryForObject("select * from user where id=?",
                new BeanPropertyRowMapper<>(User.class)
                ,id
        );
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from user where username=?",
                new BeanPropertyRowMapper<>(User.class)
                ,username
        );
    }
    public List<String> getAllUsernames() {
        return jdbcTemplate.queryForList("SELECT username FROM user",
                String.class);
    }
    @Override
    public void save(User u) {
        jdbcTemplate.update("insert into user (username, password) values (?, ?)",
                u.getUsername(), u.getPassword());
    }
}
