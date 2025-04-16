package org.lessons.spring.spring_la_mia_pizzeria_relazioni.interfaces;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.annotations.ValidDateRange;
import org.lessons.spring.spring_la_mia_pizzeria_relazioni.models.SpecialOffer;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, SpecialOffer> {

    @Override
    public boolean isValid(SpecialOffer offer, ConstraintValidatorContext context) {
        if (offer == null) return true; // handled elsewhere

        LocalDate start = offer.getStartDate();
        LocalDate end = offer.getEndDate();

        if (start == null || end == null) return true; // other validations will catch this

        // end date must be >= start AND >= today
        return (!end.isBefore(start)) && (!end.isBefore(LocalDate.now()));
    }
}
