package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.dtos.LoginFormDto;
import com.factorIt.eccomerce.security.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "Iniciar sesion", notes = "Retorna un token jwt para poder validar el usuario.")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginFormDto loginForm) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getUsername());

        final String jwt = TokenUtils.createToken(loginForm.getUsername(), loginForm.getPassword());
        return ResponseEntity.ok(jwt);
    }


}