package com.example.parameterization.Repository;

import com.example.parameterization.Entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringRepo extends JpaRepository<Monitoring, Integer> {
}
