package com.factorIt.eccomerce.repositories;

import com.factorIt.eccomerce.models.ShoppingCartProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductsRepository extends JpaRepository<ShoppingCartProducts , Long> {

}
