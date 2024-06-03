package com.example.parameterization.Service;

import com.example.parameterization.Entity.Consultation;
import com.example.parameterization.Entity.Monitoring;
import com.example.parameterization.Repository.ConsultRepo;
import com.example.parameterization.Repository.MonitoringRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MonitoringService {

    @Autowired
    private final MonitoringRepo monRepo;

    public void saveorUpdate(Monitoring Mon) {

        monRepo.save(Mon);
    }
}
