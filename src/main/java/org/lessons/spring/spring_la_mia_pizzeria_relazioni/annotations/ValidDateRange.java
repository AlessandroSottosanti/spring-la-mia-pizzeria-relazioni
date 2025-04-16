package org.lessons.spring.spring_la_mia_pizzeria_relazioni.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import org.lessons.spring.spring_la_mia_pizzeria_relazioni.interfaces.DateRangeValidator;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)  // Specifichiamo la classe che implementa la logica di validazione
@Target({ ElementType.TYPE })  // L'annotazione si applica alla classe, non ai singoli campi
@Retention(RetentionPolicy.RUNTIME)  // L'annotazione è disponibile anche a runtime
public @interface ValidDateRange {
    String message() default "End date must be after or equal to start date and not in the past";  // Il messaggio di errore
    Class<?>[] groups() default {};  // Utilizzato per la validazione di gruppo (opzionale)
    Class<? extends Payload>[] payload() default {};  // Utilizzato per trasmettere metadati aggiuntivi (opzionale)
}
