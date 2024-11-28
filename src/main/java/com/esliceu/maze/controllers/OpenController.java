package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.Door;
import com.esliceu.maze.services.GameService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class OpenController {

    @Autowired
    private GameService gameService;

    @PostMapping("/open")
    public String postOpen(@RequestParam String dir, @RequestParam String timePassed,HttpSession session) {
        int gameID = (int) session.getAttribute("gameID");
        Game currentGame = gameService.getGame(gameID);

        System.out.println("Direcció porta a obrir: " + dir);

        if (currentGame == null) {
            return "redirect:/start";
        }
        currentGame.setTimePassed(Integer.parseInt(timePassed));
        int currentRoomID = currentGame.getCurrentRoomID();
        Door door = null;

        door = gameService.getDoorFromDirection(dir, door, currentRoomID, currentGame);

        if (door == null) {
            return "redirect:/map";
        }

        if (gameService.hasDoorKey(currentGame, door)) {
            door.setOpen(true);

            String openedDoorsJson = currentGame.getOpenedDoors();
            Set<String> openedDoorsSet = new HashSet<>();
            if (openedDoorsJson != null && !openedDoorsJson.isEmpty()) {
                Gson gson = new Gson();
                openedDoorsSet = gson.fromJson(openedDoorsJson, new TypeToken<Set<String>>() {}.getType());
            }

            openedDoorsSet.add(String.valueOf(door.getId()));
            currentGame.setOpenedDoors(new Gson().toJson(openedDoorsSet));

            gameService.updateGame(currentGame);
        }else{
            session.setAttribute("mapMessage", "No tens la clau necessaria. La clau necesaria es: " + door.getDoorKey());
        }

        return "redirect:/map";
    }

}
