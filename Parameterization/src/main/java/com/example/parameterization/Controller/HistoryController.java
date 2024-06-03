package com.example.parameterization.Controller;


import com.example.parameterization.Entity.Consultation;
import com.example.parameterization.Entity.History;
import com.example.parameterization.Service.ConsultService;
import com.example.parameterization.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/history")
public class HistoryController {


    @Autowired
    private HistoryService hisServ;

    //add
    @PostMapping(value = "/add")
    public String addhis(@RequestBody History his)
    {
        hisServ.saveorUpdate(his);
        return "History Added successfully, ID : " + his.getHis_ky();
    }



}
