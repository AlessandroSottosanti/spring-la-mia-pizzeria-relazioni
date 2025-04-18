package org.lessons.spring.spring_la_mia_pizzeria_relazioni.controllers;

import java.util.List;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.SpecialOffer;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/menu")
public class PizzaController {

    @Autowired
    private PizzaRepo repo;

    @Autowired
    private IngredientRepository ingredientRepo;

    @GetMapping
    public String index(
            @RequestParam(name = "query", required = false) String query,
            Model model,
            HttpServletRequest request) {
        List<Pizza> pizzas;
        if (query != null && !query.isEmpty()) {
            pizzas = repo.findByNameContainingOrDescriptionContaining(query, query);
        } else {
            pizzas = repo.findAll(); // Select * from pizzas
        }

        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);
        model.addAttribute("currentUri", request.getRequestURI());

        return "pizzas/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepo.findAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }

        repo.save(formPizza);
        return "redirect:/menu";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repo.findById(id).get());
        model.addAttribute("ingredients", ingredientRepo.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        repo.save(formPizza);
        return "redirect:/menu";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/menu";
    }

    @GetMapping("/special-offers/create/{id}")
    public String createOffer(@PathVariable Integer id, Model model) {
        Pizza pizza = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setPizza(pizza);

        model.addAttribute("specialOffer", specialOffer);
        return "special-offers/create";
    }

}
