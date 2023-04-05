package com.factorIt.eccomerce.services.implement;

import com.factorIt.eccomerce.dtos.ProductDTO;
import com.factorIt.eccomerce.models.Product;
import com.factorIt.eccomerce.repositories.ProductRepository;
import com.factorIt.eccomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public ProductDTO getProductDto(Long id) {
        return productRepository.findById(id).map(ProductDTO::new).orElse(null);
    }

}
