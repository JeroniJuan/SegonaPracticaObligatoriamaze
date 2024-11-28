package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.Room;
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
public class KeyController {
    @Autowired
    GameService gameService;

    @PostMapping("/getKey")
    public String postKey(HttpSession session, @RequestParam String timePassed) {
        int gameID = (int) session.getAttribute("gameID");
        Game currentGame = gameService.getGame(gameID);
        if (currentGame == null) {
            return "redirect:/start";
        }

        currentGame.setTimePassed(Integer.parseInt(timePassed));
        int currentRoomID = currentGame.getCurrentRoomID();
        Room currentRoom = gameService.getRoom(currentRoomID);

        String currentRoomKey = currentRoom.getRoomKey();

        if (currentRoomKey != null && !currentRoomKey.isEmpty()) {
            if (currentGame.getCoinAmount() >= 1) {
                String keysJson = currentGame.getKeysGrabbed();
                Set<String> keysSet;

                if (keysJson == null || keysJson.isEmpty()) {
                    keysSet = new HashSet<>();
                } else {
                    keysSet = new Gson().fromJson(keysJson, new TypeToken<Set<String>>() {}.getType());
                }

                if (!keysSet.contains(currentRoomKey)) {
                    keysSet.add(currentRoomKey);

                    keysJson = new Gson().toJson(keysSet);
                    currentGame.setKeysGrabbed(keysJson);

                    currentGame.setCoinAmount(currentGame.getCoinAmount() - 1);
                }
            }else{
                session.setAttribute("mapMessage", "Necesitas una moneda per comprar aquesta clau.");
            }
        }

        gameService.updateGame(currentGame);
        return "redirect:/map";
    }
}
