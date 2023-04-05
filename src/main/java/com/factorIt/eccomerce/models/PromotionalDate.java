package com.factorIt.eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PromotionalDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startOfPromotion;
    private LocalDateTime endOfPromotion;

    public PromotionalDate() {
    }

    public PromotionalDate(String name, String description, LocalDateTime starOfPromotion, LocalDateTime endOfPromotion) {

        this.name = name;
        this.description = description;
        this.startOfPromotion = starOfPromotion;
        this.endOfPromotion = endOfPromotion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStarOfPromotion() {
        return  startOfPromotion;
    }

    public void setStarOfPromotion(LocalDateTime starOfPromotion) {
        this.startOfPromotion = starOfPromotion;
    }

    public LocalDateTime getEndOfPromotion() {
        return endOfPromotion;
    }

    public void setEndOfPromotion(LocalDateTime endOfPromotion) {
        this.endOfPromotion = endOfPromotion;
    }

    public Long getId() {
        return id;
    }
}
