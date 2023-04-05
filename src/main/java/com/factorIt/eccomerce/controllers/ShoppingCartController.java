package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.dtos.ShoppingCartDto;
import com.factorIt.eccomerce.services.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/all")
    @ApiOperation(value = "Obtener todos los carritos de compra ", notes = "Retorna ttodos los carritos de compra en la base de datos.")
    public List<ShoppingCartDto> getAll() {
        return shoppingCartService.getShoppingCarts();
    }


}
