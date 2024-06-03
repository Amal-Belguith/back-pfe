package com.example.parameterization.Repository;

import com.example.parameterization.Entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History, Integer> {
}
