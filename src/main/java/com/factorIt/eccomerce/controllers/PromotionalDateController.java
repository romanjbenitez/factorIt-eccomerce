package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.dtos.PromotionalDateDto;
import com.factorIt.eccomerce.services.PromotionalDateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotional-dates")
public class PromotionalDateController {
    @Autowired
    PromotionalDateService promotionalDateService;

    @ApiOperation(value = "Obtener todos las fechas promocionales ", notes = "Retorna todos las fechas promocionales en la base de datos.")
    @GetMapping("/all")
    public List<PromotionalDateDto> getAll() {
        return promotionalDateService.getPromotionalDates();
    }


}
