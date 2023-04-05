package com.factorIt.eccomerce.services;

import com.factorIt.eccomerce.dtos.PurchaseDTO;
import com.factorIt.eccomerce.dtos.UserPurchaseDTO;
import com.factorIt.eccomerce.models.Purchase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    List<PurchaseDTO> getAllPurchases();
    PurchaseDTO getPurchase(Long id);

    Double calculateTotalWithDiscount(UserPurchaseDTO userPurchaseDTO, Authentication authentication);
    void newPurchase(Purchase purchase);
    void savePurchase(Purchase purchase);
}
