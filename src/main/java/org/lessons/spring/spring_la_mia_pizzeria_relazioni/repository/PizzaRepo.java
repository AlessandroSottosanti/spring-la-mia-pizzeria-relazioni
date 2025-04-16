package org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository;

import java.util.List;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer>{

    public List<Pizza> findByNameContainingOrDescriptionContaining(String name, String description);
}
