package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.dtos.UserPurchaseDTO;
import com.factorIt.eccomerce.models.*;
import com.factorIt.eccomerce.services.*;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.factorIt.eccomerce.models.Role.VIP;
import static com.factorIt.eccomerce.models.ShopingCartType.*;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartProductService shoppingCartProductService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "Nueva Compra", notes = "Recibe un Objeto con datos del carrito y del usuario, luego hace las validaciones correspondientes, para verificar que todo este bien y por ultimo crea la compra y la almacena en la base de datos")
    @Transactional
    @PostMapping("/new")
    public ResponseEntity<Object> newPurchase(@RequestBody UserPurchaseDTO userPurchaseDTO, Authentication authentication) {
        if (userPurchaseDTO.getUserEmail().isEmpty()) {
            return new ResponseEntity<Object>("Please insert a valid email", HttpStatus.FORBIDDEN);
        }
        if (userPurchaseDTO.getProducts().isEmpty()) {
            return new ResponseEntity<Object>("There are currently no products in the cart", HttpStatus.FORBIDDEN);
        }
        if (userPurchaseDTO.getTotalAmount() <= 0) {
            return new ResponseEntity<Object>("Insert a valid amount", HttpStatus.FORBIDDEN);
        }
        if (userPurchaseDTO.getTotalWithDiscount() <= 0) {
            return new ResponseEntity<Object>("Insert a valid amount", HttpStatus.FORBIDDEN);
        }
        if (userPurchaseDTO.getTotalWithDiscount() != purchaseService.calculateTotalWithDiscount(userPurchaseDTO, authentication)){
            return new ResponseEntity<Object>("Bad discuont", HttpStatus.FORBIDDEN);
        }
        Purchase newPurchase = new Purchase(LocalDateTime.now(), userPurchaseDTO.getTotalWithDiscount());
        purchaseService.savePurchase(newPurchase);
        Users currentUser = userService.getCurrentUser(authentication);
        ShopingCartType type = currentUser.getRole().equals(VIP) ? USERVIP : userPurchaseDTO.isPromotional() ? PROMOTIONALDATE : REGULAR;
        ShoppingCart shoppingCart = new ShoppingCart(type);
        userPurchaseDTO.getProducts().forEach(product -> {
           int quantity = product.getQuantity();
            productService.getProduct(product.getId()).setStock(product.getStock() - quantity);
            ShoppingCartProducts cartProduct = new ShoppingCartProducts(shoppingCart, productService.getProduct(product.getId()));
            shoppingCartProductService.saveShoppingCartProduct(cartProduct);
            shoppingCart.addShoppingCartProducts(cartProduct);
        });
        newPurchase.setShoppingCart(shoppingCart);
        shoppingCartService.saveShoppingCart(shoppingCart);
        currentUser.addShoppingCart(shoppingCart);
        userService.saveUser(currentUser);
        purchaseService.savePurchase(newPurchase);
        shoppingCartService.saveShoppingCart( shoppingCart );
        userService.handleUserRoles(authentication);
        return new ResponseEntity<Object>("purchase completed successfully", HttpStatus.ACCEPTED);
    }

}
