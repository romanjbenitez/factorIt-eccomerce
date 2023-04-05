package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.dtos.ProductDTO;
import com.factorIt.eccomerce.services.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation(value = "Obtener todos los productos ", notes = "Retorna todos los productos en la base de datos.")
    @GetMapping("/all")
    public List<ProductDTO> getAll(){
        return productService.getProducts();
    }


}
