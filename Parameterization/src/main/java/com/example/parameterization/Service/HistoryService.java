package com.example.parameterization.Service;


import com.example.parameterization.Entity.Consultation;
import com.example.parameterization.Entity.History;
import com.example.parameterization.Repository.ConsultRepo;
import com.example.parameterization.Repository.HistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HistoryService {

    @Autowired
    private final HistoryRepo hisRepo;
    public void saveorUpdate(History his) {

        hisRepo.save(his);
    }
}
