package com.factorIt.eccomerce.repositories;

import com.factorIt.eccomerce.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
