package org.lessons.spring.spring_la_mia_pizzeria_relazioni.controllers;

import java.util.List;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.Ingredient;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientRepository ingredientRepo;

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {

        List<Ingredient> ingredients = ingredientRepo.findAll();

        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("ingredients", ingredients);

        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }        
        ingredientRepo.save(formIngredient);
        return "redirect:/ingredients";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepo.findById(id).get());
        return "ingredients/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/edit";
        }

        ingredientRepo.save(formIngredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String postMethodName(@PathVariable Integer id) {

        Ingredient ingredientToDelete = ingredientRepo.findById(id).get();

        for(Pizza linkedPizza : ingredientToDelete.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepo.delete(ingredientToDelete);

        return "redirect:/ingredients";
    }
    
    
}
