package com.example.parameterization.Service;

import com.example.parameterization.Entity.CarePlan;
import com.example.parameterization.Repository.CareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CareService {

    @Autowired
    private final CareRepo careRepo;

    public void saveorUpdate(CarePlan care) {

        careRepo.save(care);
    }
}
