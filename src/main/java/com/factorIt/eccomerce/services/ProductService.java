package com.factorIt.eccomerce.services;

import com.factorIt.eccomerce.dtos.ProductDTO;
import com.factorIt.eccomerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> getProducts();
    ProductDTO getProductDto(Long id);
    Product getProduct(Long id);
    void saveProduct(Product product);
}
