package com.factorIt.eccomerce.services.implement;

import com.factorIt.eccomerce.dtos.ProductDTO;
import com.factorIt.eccomerce.dtos.PurchaseDTO;
import com.factorIt.eccomerce.dtos.UserPurchaseDTO;
import com.factorIt.eccomerce.models.*;
import com.factorIt.eccomerce.repositories.PurchaseRepository;
import com.factorIt.eccomerce.services.PurchaseService;
import com.factorIt.eccomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.factorIt.eccomerce.models.ShopingCartType.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserService userService;

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAll().stream().map(PurchaseDTO::new).collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO getPurchase(Long id) {
        return purchaseRepository.findById(id).map(PurchaseDTO::new).orElse(null);
    }

    @Override
    public Double calculateTotalWithDiscount(UserPurchaseDTO userPurchaseDTO, Authentication authentication) {
        double  discount = 0;
        double  total = userPurchaseDTO.getTotalAmount();
        long numProducts = userPurchaseDTO.getProducts().size();
        Users currentUser = userService.getCurrentUser(authentication);
        ShopingCartType type = currentUser.getRole().toString().equals(USERVIP.toString()) ? USERVIP : userPurchaseDTO.isPromotional() ? PROMOTIONALDATE : REGULAR;
        List<Double> productPrices = userPurchaseDTO.getProducts().stream().map(ProductDTO::getAmount).collect(Collectors.toList());
        if (numProducts == 4) {
            discount = userPurchaseDTO.getTotalAmount() * 0.25;
        } else if (numProducts > 10) {
            if (type == REGULAR) {
                discount = 100;
            } else if (type == PROMOTIONALDATE) {
                discount = 300;
            } else if (type == USERVIP ) {
                double lowestPrice = Double.MAX_VALUE;
                for (double price : productPrices) {
                    if (price < lowestPrice) {
                        lowestPrice = price;
                    }
                }
                total -= lowestPrice;
                discount = 500;
            }
        }



        return total - discount;
    }


    @Override
    public void newPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
