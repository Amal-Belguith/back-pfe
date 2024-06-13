package com.care.careplan.Controller;


import com.care.careplan.Entity.Appointment;
import com.care.careplan.Entity.CarePlan;
import com.care.careplan.Service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/careplan")
public class CarePlanController {


    @Autowired
    private CareService careServ;

    //add
    @PostMapping(value = "/add")
    public String addCare(@RequestBody CarePlan care)
    {
        careServ.saveorUpdate(care);
        return "CarePlan Added successfully, ID : " + care.getCare_ky();
    }
    @GetMapping("/all")
    public List<CarePlan> getCareplans() {
        return careServ.getCareplans();
    }
    @DeleteMapping("/delete/{care_ky}")
    public void deleteCare(@PathVariable("care_ky") Integer care_ky) {
        careServ.delete(care_ky);
    }

    @GetMapping(value = "/user/{userKy}")
    public ResponseEntity<List<CarePlan>> getCareplansByUserKy(@PathVariable Integer userKy) {
        List<CarePlan> careplans = careServ.getCareplansByUserKy(userKy);
        if (careplans != null && !careplans.isEmpty()) {
            return ResponseEntity.ok(careplans);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
