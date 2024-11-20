package com.esliceu.maze.dao;

import com.esliceu.maze.model.Door;
import com.esliceu.maze.model.GameMap;
import com.esliceu.maze.model.Room;
import com.esliceu.maze.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImplement implements GameDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public GameMap getMap(int mapid) {
        return jdbcTemplate.queryForObject("select * from gameMap where id=?",
                new BeanPropertyRowMapper<>(GameMap.class)
                ,mapid
        );
    }

    @Override
    public Room getRoom(int roomid) {
        return jdbcTemplate.queryForObject("select * from room where id=?",
                new BeanPropertyRowMapper<>(Room.class)
                ,roomid
        );
    }

    @Override
    public Door getDoor(int doorid) {
        return jdbcTemplate.queryForObject("select * from door where id=?",
                new BeanPropertyRowMapper<>(Door.class)
                ,doorid
        );
    }
}
