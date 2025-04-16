package org.lessons.spring.spring_la_mia_pizzeria_relazioni.models;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity 
@Table(name = "pizzas") 
public class Pizza {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    @NotBlank(message = "required")
    @Column(name = "pizza_name")
    @Size(min = 2, max = 15, message = "the name must be of 3 or more characters and max 15 characters")
    private String name;


    @NotBlank(message = "required")
    @Size(max = 50, message = "description must be less or equals to 50 characters")
    private String description;

    @Column(name = "pizza_image")
    private String image;

    @NotNull
    @Min( value = 0, message = "price can not be less then 0")
    private BigDecimal price = BigDecimal.ZERO;


    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpecialOffer> specialOffers;
    
    // Getter & Setter
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public BigDecimal getPrice() {
        if (this.price == null) {
            return BigDecimal.ZERO;
        }
        return price.setScale(2, RoundingMode.UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.UP);
    }


    public List<SpecialOffer> getSpecialOffers() {
        return this.specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

}
