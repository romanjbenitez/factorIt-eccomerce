package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.PromotionalDate;

import java.time.LocalDateTime;

public class PromotionalDateDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startOfPromotion;
    private LocalDateTime endOfPromotion;

    public PromotionalDateDto() {
    }
    public PromotionalDateDto(PromotionalDate promotionalDate) {
        this.id = promotionalDate.getId();
        this.name = promotionalDate.getName();
        this.description = promotionalDate.getDescription();
        this.startOfPromotion = promotionalDate.getStarOfPromotion();
        this.endOfPromotion = promotionalDate.getEndOfPromotion();
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

    public LocalDateTime getStartOfPromotion() {
        return startOfPromotion;
    }

    public void setStartOfPromotion(LocalDateTime starOfPromotion) {
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
