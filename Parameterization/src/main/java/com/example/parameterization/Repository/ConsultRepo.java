package com.example.parameterization.Repository;

import com.example.parameterization.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepo extends JpaRepository<Consultation,Integer> {
}
