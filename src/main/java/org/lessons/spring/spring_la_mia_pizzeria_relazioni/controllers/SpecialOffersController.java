package org.lessons.spring.spring_la_mia_pizzeria_relazioni.controllers;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.Pizza;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.SpecialOffer;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository.PizzaRepo;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SpecialOffersController {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Autowired
    private SpecialOfferRepository offerRepo;

    @PostMapping("/special-offers/save")
    public String saveSpecialOffer(
            @Valid @ModelAttribute("specialOffer") SpecialOffer specialOffer, BindingResult bindingResult,
            Model model) {


        Integer pizzaId = specialOffer.getPizza().getId();

        Pizza pizza = pizzaRepo.findById(pizzaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        specialOffer.setPizza(pizza); // Ricollega la pizza vera e propria

        
        if (bindingResult.hasErrors()) {
            return "/special-offers/create";
        }
        offerRepo.save(specialOffer);

        // Dopo il salvataggio, reindirizza al dettaglio della pizza
        return "redirect:/menu/" + pizzaId;
    }

    @GetMapping("/special-offers/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("specialOffer", offerRepo.findById(id).get());
        return "special-offers/edit";
    }
    
    @PostMapping("/special-offers/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOffer, BindingResult bindingResult ,  @PathVariable Integer id, Model model) {

        if(bindingResult.hasErrors()){
            return "special-offers/edit";
        }

        offerRepo.save(specialOffer);
        return "redirect:/menu/" + specialOffer.getPizza().getId();
    }
    
    
}
