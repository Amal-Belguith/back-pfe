package com.example.parameterization.Controller;

import com.example.parameterization.Entity.PhysicalTreatment;
import com.example.parameterization.Service.PhyTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/phytreatments")
public class PhyTreatmentController {
    private final PhyTreatmentService phyTreatmentService;

    @Autowired
    public PhyTreatmentController(PhyTreatmentService phyTreatmentService) {
        this.phyTreatmentService = phyTreatmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhysicalTreatment>> getAllTreatments() {
        List<PhysicalTreatment> treatments = phyTreatmentService.getAllTreatments();
        return new ResponseEntity<>(treatments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhysicalTreatment> getTreatmentById(@PathVariable("id") long id) {
        PhysicalTreatment treatment = phyTreatmentService.getTreatmentById(id);
        if (treatment != null) {
            return new ResponseEntity<>(treatment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveTreatment(@RequestBody PhysicalTreatment treatment) {
        if (phyTreatmentService.treatmentExists(treatment.getPhyTrName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Physical Treatment already exists");
        } else {
            PhysicalTreatment savedTreatment = phyTreatmentService.saveTreatment(treatment);
            return ResponseEntity.ok(savedTreatment);
        }
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<?> updateTreatment(@RequestBody PhysicalTreatment physicalTreatment, @PathVariable("id") long idtreatment ) {
        if (phyTreatmentService.treatmentExists(physicalTreatment.getPhyTrName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Physical Treatment already exists");
        } else {

            return new ResponseEntity<>(phyTreatmentService.updateTreatment(physicalTreatment, idtreatment), HttpStatus.OK);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable("id") long id) {
        if (phyTreatmentService.getTreatmentById(id) != null) {
            phyTreatmentService.deleteTreatment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<PhysicalTreatment>> searchPhyTreatmentByCriteria(@RequestParam("criteria") String criteria) {
        List<PhysicalTreatment> treatments = phyTreatmentService.retrievePhyTreatmentByCriteria(criteria);
        return new ResponseEntity<>(treatments, HttpStatus.OK);
    }

    //exist
    @GetMapping("/exists")
    public boolean checkIftrExists(@RequestParam String phyTrName) {
        return phyTreatmentService.treatmentExists(phyTrName);
    }

}
