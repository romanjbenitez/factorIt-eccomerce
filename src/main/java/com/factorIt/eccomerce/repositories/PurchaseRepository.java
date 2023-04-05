package com.factorIt.eccomerce.repositories;

import com.factorIt.eccomerce.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
