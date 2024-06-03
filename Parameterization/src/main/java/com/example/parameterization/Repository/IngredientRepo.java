package com.example.parameterization.Repository;

import com.example.parameterization.Entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Integer>{

    boolean existsByIngredientName(String ingredientName);
}
