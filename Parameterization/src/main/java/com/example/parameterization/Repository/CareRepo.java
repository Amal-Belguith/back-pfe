package com.example.parameterization.Repository;

import com.example.parameterization.Entity.CarePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareRepo extends JpaRepository<CarePlan,Integer> {
}
