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
public class CoinController {
    @Autowired
    GameService gameService = new GameService();
    @PostMapping("/getCoin")
    public String postCoin(HttpSession session, @RequestParam String timePassed) {
        int gameID = (int) session.getAttribute("gameID");
        Game currentGame = gameService.getGame(gameID);
        if (currentGame == null) {
            return "redirect:/start";
        }
        currentGame.setTimePassed(Integer.parseInt(timePassed));
        int currentRoomID = currentGame.getCurrentRoomID();
        Room currentRoom = gameService.getRoom(currentRoomID);

        if (currentRoom.hasCoin()) {
            String coinsGrabbedJson = currentGame.getCoinsGrabbed();
            Set<String> coinsGrabbedSet;

            if (coinsGrabbedJson == null || coinsGrabbedJson.isEmpty()) {
                coinsGrabbedSet = new HashSet<>();
            } else {
                coinsGrabbedSet = new Gson().fromJson(coinsGrabbedJson, new TypeToken<Set<String>>() {}.getType());
            }

            String currentRoomName = currentRoom.getRoomName();

            if (!coinsGrabbedSet.contains(currentRoomName)) {
                coinsGrabbedSet.add(currentRoomName);

                coinsGrabbedJson = new Gson().toJson(coinsGrabbedSet);
                currentGame.setCoinsGrabbed(coinsGrabbedJson);

                currentGame.sumCoin();
            }
        }
        gameService.updateGame(currentGame, (String) session.getAttribute("user"));
        return "redirect:/map";
    }
}
