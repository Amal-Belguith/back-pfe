package com.example.parameterization.Repository;

import com.example.parameterization.Entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepo extends JpaRepository<History, Integer> {
    History findByUserKy(Integer userKy);

    boolean existsByUserKy(Integer userKy);
}
