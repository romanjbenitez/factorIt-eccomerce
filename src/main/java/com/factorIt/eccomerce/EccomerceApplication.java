package com.factorIt.eccomerce;

import com.factorIt.eccomerce.models.*;
import com.factorIt.eccomerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.factorIt.eccomerce.models.ShopingCartType.*;

@SpringBootApplication
@EnableSwagger2
public class EccomerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EccomerceApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SelectedDate selectedDate;
    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      ProductRepository productRepository,
                                      ShoppingCartRepository shoppingCartRepository,
                                      PromotionalDateRepository promotionalDateRepository,
                                      ShoppingCartProductsRepository shoppingCartProductsRepository,
                                      PurchaseRepository purchaseRepository) {
        return (args) -> {
            System.out.println("aplicacion iniciada");
            System.out.println(selectedDate.getSelectDate());

            Users juanPerez = new Users("Juan", "Pérez", "juanperez@gmail.com", passwordEncoder.encode("1234"), LocalDateTime.now(), Role.NORMAL);
            userRepository.save(juanPerez);
            Users romanBenitez = new Users("Roman", "Benitez", "romanbenitez@gmail.com", passwordEncoder.encode("1234"), LocalDateTime.now(), Role.VIP);
            userRepository.save(romanBenitez);

            List<Product> productList = new ArrayList<>();
            productList.add(new Product("Jean", "Pantalón de jean para hombre", 10000, "https://http2.mlstatic.com/D_NQ_NP_611173-MLA51653920339_092022-O.jpg", 50));
            productList.add(new Product("Camisa", "Camisa de vestir para hombre", 12999, "https://www.devre.la/media/catalog/product/d/e/devre-camisa-vestir_44d000332-060_001.jpg", 30));
            productList.add(new Product("Campera", "Campera de cuero para hombre", 19999, "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/210/880/products/art-140-negro-ml1-fff5665549e1296e1716336778090658-640-0.webp", 20));
            productList.add(new Product("Vestido", "Vestido de noche para mujer", 7999, "https://cdn0.casamientos.com.ar/article-dress/6603/original/1280/jpg/m343066.jpeg", 15));
            productList.add(new Product("Blusa", "Blusa de seda para mujer", 4999, "https://i.pinimg.com/originals/3f/6c/e7/3f6ce7449e52f5dbf91f42152d85d4bf.jpg", 25));
            productList.add(new Product("Pollera", "Pollera de jean para mujer", 6999, "https://d2r9epyceweg5n.cloudfront.net/stores/942/354/products/mojojeansecomm_1861-28b73aa3adb3df3b2016678313666298-640-0.jpg", 30));
            productList.add(new Product("Saco", "Saco para dama", 18999, "https://i.pinimg.com/236x/fd/a5/e0/fda5e097a3c958d3c55eb80b1111e30b.jpg", 10));
            productList.add(new Product("Short", "Short de playa para hombre", 6999, "https://http2.mlstatic.com/D_NQ_NP_710709-MLA52779644512_122022-W.jpg", 40));
            productList.add(new Product("Medias", "Medias para deporte", 1999, "https://media.revistagq.com/photos/5db984d690f9460008eda502/master/w_1600%2Cc_limit/9.jpg", 50));
            productList.add(new Product("Remera", "Remera para hombre", 1699, "https://www.devre.la/media/catalog/product/d/e/devre-remera_05d000101-085_001_1.jpg", 30));
            productList.add(new Product("Buso", "Buso deportivo de mujer", 8999, "https://http2.mlstatic.com/D_NQ_NP_630658-MLA47412408492_092021-O.jpg", 40));
            productList.add(new Product("Pantalon Deportivo", "Pantalon deportivo de hombre", 9999, "https://http2.mlstatic.com/D_NQ_NP_624022-MLA50056063421_052022-O.jpg", 40));

            productRepository.saveAll(productList);

            ShoppingCart shoppingCartRegular = new ShoppingCart(REGULAR);
            ShoppingCart shoppingCartPromotionalDate = new ShoppingCart(PROMOTIONALDATE);
            ShoppingCart shoppingCartUserVip = new ShoppingCart(USERVIP);
            shoppingCartRepository.save(shoppingCartRegular);
            shoppingCartRepository.save(shoppingCartPromotionalDate);
            shoppingCartRepository.save(shoppingCartUserVip);

            ShoppingCartProducts shoppingCartProducts1 = new ShoppingCartProducts(shoppingCartRegular , productList.get(1));
            ShoppingCartProducts shoppingCartProducts2 = new ShoppingCartProducts(shoppingCartRegular , productList.get(4));

            shoppingCartProductsRepository.save(shoppingCartProducts1);
            shoppingCartProductsRepository.save(shoppingCartProducts2);

            shoppingCartRegular.addShoppingCartProducts(shoppingCartProducts1);
            shoppingCartRegular.addShoppingCartProducts(shoppingCartProducts2);
            Purchase purchase = new Purchase(LocalDateTime.now(), 1234);
            shoppingCartRegular.setPurchase(purchase);

            purchaseRepository.save(purchase);
            shoppingCartRepository.save(shoppingCartRegular);
            shoppingCartRepository.save(shoppingCartPromotionalDate);
            shoppingCartRepository.save(shoppingCartUserVip);



            LocalDateTime startOfPromotion = LocalDateTime.of(2023, Month.APRIL, 1, 0, 0);
            LocalDateTime endOfPromotion = LocalDateTime.of(2023, Month.APRIL, 16, 23, 59, 59);
            PromotionalDate easterPromotion = new PromotionalDate("Promoción de Pascua", "Descuentos especiales por Semana Santa", startOfPromotion, endOfPromotion);
            promotionalDateRepository.save(easterPromotion);

            juanPerez.addShoppingCart(shoppingCartRegular);
            userRepository.save(juanPerez);
            shoppingCartRepository.save(shoppingCartRegular);
        };
    }

    ;
};
