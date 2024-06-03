package com.example.parameterization.Controller;

import com.example.parameterization.Entity.SurgicalProcedure;
import com.example.parameterization.Service.SurgicalProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/procedures")
public class SurgicalProcedureController {
    @Autowired
    private SurgicalProcedureService Iservice;

    @GetMapping
    public List<SurgicalProcedure> getAllProcedures() {
        return Iservice.getAllProcedures();
    }

    @GetMapping ("/details/{cptky}")
    public SurgicalProcedure getProcedureById(@PathVariable("cptky") Integer cptky) {
        return Iservice.getProcedureById(cptky);
    }
    @PostMapping ("/add")
    public ResponseEntity<?> addProcedure(@RequestBody SurgicalProcedure procedure) {
        if (Iservice.surgicalExists(procedure.getCptCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Surgical already exists");
        } else {
            SurgicalProcedure surgical= Iservice.addProcedure(procedure);
            return ResponseEntity.ok(surgical);
        }
    }
    @PutMapping("/update/{cptky}")
    public ResponseEntity<?> updateProcedure(@PathVariable("cptky") Integer cptky, @RequestBody SurgicalProcedure procedure) {
        if (Iservice.surgicalExists(procedure.getCptCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Surgical already exists");
        } else {
            SurgicalProcedure surg = Iservice.updateProcedure(cptky, procedure);
            return ResponseEntity.ok(surg);

        }
    }
    @DeleteMapping("/delete/{cptky}")
    public void deleteProcedure(@PathVariable("cptky") Integer cptky){
        Iservice.deleteProcedure(cptky);
    }

    @GetMapping("/search")
    public List<SurgicalProcedure> searchProcedures(@RequestParam(required = false) String cptCode) {
        if (cptCode != null && !cptCode.isEmpty()) {
            return Iservice.searchByCptCode(cptCode);
        } else {
            return Iservice.getAllProcedures();
        }
    }
    //exist
    @GetMapping("/exists")
    public boolean checkIfSurgicalExists(@RequestParam String cptCode) {
        return Iservice.surgicalExists(cptCode);
    }


}



