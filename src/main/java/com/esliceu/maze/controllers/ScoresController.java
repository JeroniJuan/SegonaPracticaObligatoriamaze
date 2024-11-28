package com.esliceu.maze.controllers;

import com.esliceu.maze.model.Game;
import com.esliceu.maze.model.Records;
import com.esliceu.maze.services.GameService;
import com.esliceu.maze.services.RecordsService;
import com.esliceu.maze.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScoresController {
    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Autowired
    RecordsService recordsService;

    @GetMapping("/scores")
    public String getScores(HttpSession session, Model model){
        // Obtener el ID del usuario desde la sesión
        String userStr = (String) session.getAttribute("user");
        int userid = userService.findByUsername(userStr).getId();

        // Obtener todas las partidas del usuario
        List<Records> userRecords = recordsService.getRecordsByUserId(userid);

        // Obtener el top 5 de partidas con menos movimientos
        List<Records> top5Movements = recordsService.getTop5RecordsWithLeastMovements();

        // Obtener el top 5 de partidas con menos tiempo
        List<Records> top5Time = recordsService.getTop5RecordsWithLeastTime();

        // Pasar todos los datos al modelo para ser usados en la vista
        model.addAttribute("userRecords", userRecords);
        model.addAttribute("top5Movements", top5Movements);
        model.addAttribute("top5Time", top5Time);

        return "scores";
    }
}
