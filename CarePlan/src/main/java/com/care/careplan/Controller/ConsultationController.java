package com.care.careplan.Controller;


import com.care.careplan.Entity.Consultation;
import com.care.careplan.Service.ConsultService;
import com.care.careplan.dto.ConsultationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultService Conser;

    //add
    @PostMapping(value = "/add")
    public String addcon(@RequestBody Consultation Con) {
        Conser.saveorUpdate(Con);
        return "Consultation Added successfully, ID : " + Con.getCon_ky();
    }

    //GetAll
    @GetMapping("/all")
    public List<Consultation> getConsultation() {
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

    @GetMapping("/{id}")
    public ConsultationDTO getConsultationDetails(@PathVariable Integer id) {
        return Conser.getConsultationDetails(id);
    }
}
