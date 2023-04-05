package com.factorIt.eccomerce.services;

import com.factorIt.eccomerce.dtos.RegisterUserDto;
import com.factorIt.eccomerce.dtos.UsersDTO;
import com.factorIt.eccomerce.dtos.UsersInfoDto;
import com.factorIt.eccomerce.models.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UsersDTO> getUsers();
    Users getCurrentUser(Authentication authentication);
    UsersDTO getUser(Long id);

    Users getUserByEmail(String email);
    void handleUserRoles(Authentication authentication);

    void newUser(RegisterUserDto registerUserDto);
    List<UsersInfoDto> getUserInfo();
  void saveUser (Users users);
}
