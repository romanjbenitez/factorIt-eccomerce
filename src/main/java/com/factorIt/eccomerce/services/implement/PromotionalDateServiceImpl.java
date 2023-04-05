package com.factorIt.eccomerce.services.implement;

import com.factorIt.eccomerce.dtos.PromotionalDateDto;
import com.factorIt.eccomerce.repositories.PromotionalDateRepository;
import com.factorIt.eccomerce.services.PromotionalDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionalDateServiceImpl implements PromotionalDateService {
    @Autowired
    PromotionalDateRepository promotionalDateRepository;
    @Override
    public List<PromotionalDateDto> getPromotionalDates() {
        return promotionalDateRepository.findAll().stream().map(PromotionalDateDto::new).collect(Collectors.toList());
    }

    @Override
    public PromotionalDateDto getPromotionalDate(Long id) {
        return promotionalDateRepository.findById(id).map(PromotionalDateDto::new).orElse(null);
    }
}
