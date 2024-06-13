package com.care.careplan.Client;


import com.example.parameterization.dto.MedicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "medication-service")
public interface MedicationFeignClient {

    @GetMapping("/medication/{id}")
    MedicationResponse getMedicationById(@PathVariable("id") Integer id);

    @GetMapping("/medication/all")
    List<MedicationResponse> getMedications();
}
