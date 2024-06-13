package com.care.careplan.Client;

import com.example.parameterization.Entity.Allergy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "allergy-service")
public interface AllergyFeignClient {

    @GetMapping("/allergies/{id}")
    Allergy getAllergyById(@PathVariable("id") Long id);

    @GetMapping("/allergies/all")
    List<Allergy> getAllAllergies();
}

