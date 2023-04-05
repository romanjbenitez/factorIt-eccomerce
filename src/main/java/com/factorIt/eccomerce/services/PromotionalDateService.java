package com.factorIt.eccomerce.services;

import com.factorIt.eccomerce.dtos.PromotionalDateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionalDateService {
    List<PromotionalDateDto> getPromotionalDates();
    PromotionalDateDto getPromotionalDate(Long id);
}
