package org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
