package com.care.careplan.Repository;


import com.care.careplan.Entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringRepo extends JpaRepository<Monitoring,Integer> {
}
