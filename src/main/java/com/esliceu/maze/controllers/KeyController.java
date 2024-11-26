package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.Room;
import com.esliceu.maze.services.GameService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;
@Controller
public class KeyController {
    @Autowired
    GameService gameService;

    @PostMapping("/getKey")
    public String postKey(HttpSession session) {
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame == null) {
            return "redirect:/start";
        }

        int currentRoomID = currentGame.getCurrentRoomID();
        Room currentRoom = gameService.getRoom(currentRoomID);

        String currentRoomKey = currentRoom.getRoomKey();

        if (currentRoomKey != null && !currentRoomKey.isEmpty()) {
            if (currentGame.getCoinAmount() >= 1) {
                String keysJson = currentGame.getKeys();
                Set<String> keysSet;

                if (keysJson == null || keysJson.isEmpty()) {
                    keysSet = new HashSet<>();
                } else {
                    keysSet = new Gson().fromJson(keysJson, new TypeToken<Set<String>>() {}.getType());
                }

                if (!keysSet.contains(currentRoomKey)) {
                    keysSet.add(currentRoomKey);

                    keysJson = new Gson().toJson(keysSet);
                    currentGame.setKeys(keysJson);

                    currentGame.setCoinAmount(currentGame.getCoinAmount() - 1);
                }
            }
        }

        session.setAttribute("currentGame", currentGame);
        return "redirect:/map";
    }
}
