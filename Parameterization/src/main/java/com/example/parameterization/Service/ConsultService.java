package com.example.parameterization.Service;

import com.example.parameterization.Entity.Appointment;
import com.example.parameterization.Entity.Consultation;
import com.example.parameterization.Entity.Ingredient;
import com.example.parameterization.Repository.ConsultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultService {

    @Autowired
    private final ConsultRepo conRepo;

    public void saveorUpdate(Consultation Con) {

        conRepo.save(Con);
    }

    public List<Consultation> getConsultations () {
        return conRepo.findAll();
    }
}
