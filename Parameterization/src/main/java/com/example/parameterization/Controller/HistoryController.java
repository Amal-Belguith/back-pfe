package com.example.parameterization.Controller;


import com.example.parameterization.Entity.History;
import com.example.parameterization.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/history")
public class HistoryController {


    @Autowired
    private HistoryService hisServ;


    @PostMapping(value = "/add")
    public ResponseEntity<String> addHis(@RequestBody History his) {
        Integer userKy = his.getUserKy();
        if (hisServ.historyExistsForUser(userKy)) {
            return ResponseEntity.badRequest().body("History already exists for this user");
        } else {
            hisServ.saveOrUpdate(his);
            return ResponseEntity.ok("History Added successfully, ID : " + his.getHis_ky());
        }
    }

    @GetMapping(value = "/check/{userKy}")
    public boolean checkHistoryForUser(@PathVariable Integer userKy) {
        return hisServ.historyExistsForUser(userKy);
    }

    // Exemple de méthode pour récupérer l'historique déchiffré
    @GetMapping(value = "/all")
    public List<History> getAllHistories() {
        return hisServ.getHistories();
    }


    @GetMapping(value = "/user/{userKy}")
    public ResponseEntity<History> getHistoryByUserKy(@PathVariable Integer userKy) {
        History history = hisServ.getHistoryByUserKy(userKy);
        if (history != null) {
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
