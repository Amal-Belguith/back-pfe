package com.example.parameterization.Controller;


import com.example.parameterization.Entity.History;
import com.example.parameterization.Entity.Monitoring;
import com.example.parameterization.Service.HistoryService;
import com.example.parameterization.Service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monSer;

    //add
    @PostMapping(value = "/add")
    public String addMon(@RequestBody Monitoring mon)
    {
        monSer.saveorUpdate(mon);
        return "Monitoring Added successfully, ID : " + mon.getMon_ky();
    }

}
