package com.care.careplan.Controller;

import com.care.careplan.Entity.Monitoring;
import com.care.careplan.Service.MonitoringService;
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

    @GetMapping("/{id}")
    public Monitoring getMonitoring(@PathVariable Integer id) {
        return monSer.getMonitoringWithAllergies(id);
    }



}
