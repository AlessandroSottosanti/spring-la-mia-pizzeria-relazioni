package org.lessons.spring.spring_la_mia_pizzeria_relazioni.models;

import java.time.LocalDate;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.annotations.ValidDateRange;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@ValidDateRange
@Entity
@Table(name= "special_offers")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min= 2, message = "the offer title must be at least of 2 characters")
    @NotBlank
    private String title;

    @NotNull(message = "The borrowing date can not be null")
    @PastOrPresent( message = "borrowing date cannot be set in the future")
    private LocalDate startDate;

    @NotNull(message = "The borrowing date can not be null")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;


    // Getter & Setter
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
