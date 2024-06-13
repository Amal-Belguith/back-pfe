/*
package com.example.parameterization.Controller;

import com.example.parameterization.Entity.Consultation;
import com.example.parameterization.Entity.Ingredient;
import com.example.parameterization.Service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private  ConsultService Conser;

    //add
    @PostMapping(value = "/add")
    public String addapp(@RequestBody Consultation Con)
    {
        Conser.saveorUpdate(Con);
        return "Consultation Added successfully, ID : " + Con.getCon_ky();
    }
    //GetAll
    @GetMapping("/all")
    public List<Consultation> getConsultation(){
        return Conser.getConsultations();
    }

    @GetMapping(value = "/user/{userKy}")
    public ResponseEntity<List<Consultation>> getConsultationsByUserKy(@PathVariable Integer userKy) {
        List<Consultation> consultations = Conser.getConsultationsByUserKy(userKy);
        if (consultations != null && !consultations.isEmpty()) {
            return ResponseEntity.ok(consultations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
*/
