package com.example.parameterization.Controller;


import com.example.parameterization.Entity.CarePlan;
import com.example.parameterization.Entity.History;
import com.example.parameterization.Service.CareService;
import com.example.parameterization.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/careplan")
public class CarePlanController {


    @Autowired
    private CareService careServ;

    //add
    @PostMapping(value = "/add")
    public String addhis(@RequestBody CarePlan care)
    {
        careServ.saveorUpdate(care);
        return "CarePlan Added successfully, ID : " + care.getCare_ky();
    }
}
