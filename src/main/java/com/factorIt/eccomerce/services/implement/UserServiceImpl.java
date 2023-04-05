package com.factorIt.eccomerce.services.implement;

import com.factorIt.eccomerce.dtos.PurchaseDTO;
import com.factorIt.eccomerce.dtos.RegisterUserDto;
import com.factorIt.eccomerce.dtos.UsersDTO;
import com.factorIt.eccomerce.dtos.UsersInfoDto;
import com.factorIt.eccomerce.models.Role;
import com.factorIt.eccomerce.models.ShoppingCart;
import com.factorIt.eccomerce.models.Users;
import com.factorIt.eccomerce.repositories.UserRepository;
import com.factorIt.eccomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UsersDTO> getUsers() {
        return userRepository.findAll().stream().map(UsersDTO::new).collect(Collectors.toList());
    }

    @Override
    public Users getCurrentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName()).orElse(null);
    }

    @Override
    public UsersDTO getUser(Long id) {
        return userRepository.findById(id).map(UsersDTO::new).orElse(null);
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void handleUserRoles(Authentication authentication) {
        Users currentUser = getCurrentUser(authentication);
        List<Double> purchasesMonthAmountListFiltered = currentUser.getShoppingCarts().stream().map(ShoppingCart::getPurchase).map(PurchaseDTO::new).map(PurchaseDTO::getAmount).filter(amount -> amount >= 10000).collect(Collectors.toList());
        if (!purchasesMonthAmountListFiltered.isEmpty()) {
            currentUser.setRole(Role.VIP);
        }

        List<Double> purchasesMonthAmountList = currentUser.getShoppingCarts().stream()
                .map(ShoppingCart::getPurchase)
                .map(PurchaseDTO::new)
                .filter(purchase -> purchase.getDate().getMonth().equals(LocalDate.now().getMonth()))
                .map(PurchaseDTO::getAmount)
                .collect(Collectors.toList());

        if (purchasesMonthAmountList.isEmpty() && currentUser.getRole() == Role.VIP) {
            currentUser.setRole(Role.NORMAL);
        }
        userRepository.save(currentUser);
    }

    @Override
    public void newUser(RegisterUserDto registerUserDto) {
        Users user = new Users(registerUserDto.getFirsName(), registerUserDto.getLastName(), registerUserDto.getEmail(), registerUserDto.getPassword(), LocalDateTime.now(), Role.NORMAL);
        userRepository.save(user);
    }

    @Override
    public List<UsersInfoDto> getUserInfo() {
        return userRepository.findAll().stream().map(UsersInfoDto::new).collect(Collectors.toList());
    }

    @Override
    public void saveUser(Users users) {
        userRepository.save(users);
    }
}
