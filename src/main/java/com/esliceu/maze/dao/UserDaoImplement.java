package com.esliceu.maze.dao;

import com.esliceu.maze.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImplement implements UserDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id=?",
                new BeanPropertyRowMapper<>(User.class)
                ,id
        );
    }

    @Override
    public void save(User u) {
        jdbcTemplate.update("insert into person (name, password) values (?, ?)",
                u.getUsername(), u.getPassword());
    }
}
