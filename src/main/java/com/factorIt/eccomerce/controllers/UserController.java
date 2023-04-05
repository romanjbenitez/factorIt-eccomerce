package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.dtos.RegisterUserDto;
import com.factorIt.eccomerce.dtos.UsersDTO;
import com.factorIt.eccomerce.dtos.UsersInfoDto;
import com.factorIt.eccomerce.security.TokenUtils;
import com.factorIt.eccomerce.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;
    @ApiOperation(value = "Obtener todos los usuarios", notes = "Retorna todos los usuarios en la base de datos.")
    @GetMapping("/all")
    public List<UsersDTO> getAll(){
        return userService.getUsers();
    }

    @ApiOperation(value = "Obtener el usuario actual", notes = "Retorna solamente el usario que esta autenticado en este momento.")
    @GetMapping("/current")
    public UsersDTO getCurrentUser(Authentication authentication){
    return  new UsersDTO(userService.getCurrentUser(authentication));
    }
    @ApiOperation(value = "Obtener informacion de usuarios", notes = "Retorna una lista usuarios con informacion correspondiente de cada uno.")
    @GetMapping("/users-info")
    public List<UsersInfoDto> getAllUserInfo(){
        return userService.getUserInfo();
    }


    @ApiOperation(value = "Crear un nuevo usario", notes = "Crea un nuevo usuario en la base de datos.")
    @PostMapping("/new")
    @Transactional
    public ResponseEntity<Object> newUser(@RequestBody RegisterUserDto user) {
        if(user.getEmail().isEmpty() || user.getFirsName().isEmpty() || user.getLastName().isEmpty() || user.getPassword().isEmpty()){
            return  new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if(userService.getUserByEmail(user.getEmail()) != null){
            return  new ResponseEntity<>("Invalid email", HttpStatus.FORBIDDEN);
        };

        return new ResponseEntity<>("User created suscessfully", HttpStatus.CREATED);
    }
}
