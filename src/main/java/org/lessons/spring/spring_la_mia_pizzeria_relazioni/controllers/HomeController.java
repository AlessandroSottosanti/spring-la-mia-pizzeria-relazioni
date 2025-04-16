package org.lessons.spring.spring_la_mia_pizzeria_relazioni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI().equals("/") ? "/" : "");
        return "home";
    }
}
