package com.example.parameterization.Controller;

import com.example.parameterization.Entity.Medication;
import com.example.parameterization.Service.MedicationService;
import com.example.parameterization.dto.MedicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.parameterization.Repository.IngredientRepo;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/parameterization")
public class MedicationController {

    @Autowired
    private MedicationService MSer;
    private final IngredientRepo ingredientRepo;

    public MedicationController(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    //Upload
    @PostMapping("/upload-data-medication")
    public ResponseEntity<?> uploadMedicationsData(@RequestParam("file") MultipartFile ifile) {
        if (ifile.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("Message" , "Empty file uploaded."));
        }

        if (!MSer.isValidExcelFile(ifile)) {
            return ResponseEntity.badRequest().body(Map.of("Message" , "Invalid file format. Please upload a valid Excel file."));
        }

        try {
            List<Medication> medications = MSer.getMedicationsDataFromExcel(ifile.getInputStream(), ingredientRepo);

            for (Medication medication : medications) {

                if (MSer.medicationExists(medication.getMedicationName(), medication.getMedicationCode())) {
                    return ResponseEntity.badRequest().body(Map.of("Message", "Medication '" + medication.getMedicationName() + "' or code '" + medication.getMedicationCode() + "' already exists."));
                }
            }


            MSer.savemedicationfile(ifile);

            return ResponseEntity.ok(Map.of("Message" , "Medications data uploaded and saved to database successfully"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("Message" , "Error processing file."));
        }
    }

    //GetAll
    @GetMapping("/all-medication")
    public List<MedicationResponse> getMedications(){
        return MSer.getMedicationResponses();
    }

    //Add
    @PostMapping(value="/add-medication")
    public ResponseEntity<?> saveMedication(@RequestBody Medication imedications) {
        if (MSer.medicationExists(imedications.getMedicationName(), imedications.getMedicationCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Medication already exist");
        } else {

            Medication saveMedication = MSer.saveorUpdate(imedications);
            return ResponseEntity.ok(saveMedication);
        }
    }

    @PutMapping(value="/edit-medication/{medication_ky}")
    public ResponseEntity<?> updateMedication(@RequestBody Medication imedication, @PathVariable(name="medication_ky") Integer iMedication_Ky) {
        try {


                Medication existingMedication = MSer.findById(iMedication_Ky);

                // Mettre à jour les attributs du médicament existant
                existingMedication.setMedicationName(imedication.getMedicationName());
                existingMedication.setMedicationCode(imedication.getMedicationCode());
                existingMedication.setMedicationType(imedication.getMedicationType());
                existingMedication.setMedicationStrength(imedication.getMedicationStrength());
                existingMedication.setMedicationDosageForm(imedication.getMedicationDosageForm());

                // Mettre à jour les liens des ingrédients avec le médicament existant
                MSer.updateIngredientLinks(existingMedication, imedication.getMedicIngredientLinks());

                Medication updatedMedication = MSer.saveorUpdate(existingMedication);

                return ResponseEntity.ok(updatedMedication);

        } catch (Exception e) {
            // Gérer les erreurs ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete
    @DeleteMapping("/delete-medication/{Medication_Ky}")
    private void deleteMedication(@PathVariable("Medication_Ky")Integer iMedication_Ky)
    {
        MSer.delete(iMedication_Ky);
    }

    //get medication
    @RequestMapping("/details-medication/{medication_ky}")
    private ResponseEntity<MedicationResponse> getMedicationById(@PathVariable(name="medication_ky")Integer imedication_ky)
    {
        return MSer.getMedicationById(imedication_ky);
    }
    //exist
    @GetMapping("/exists-medication")
    public boolean checkIfMedicationExists(@RequestParam String medicationName, @RequestParam String medicationCode) {
        return MSer.medicationExists(medicationName, medicationCode);
    }


}
