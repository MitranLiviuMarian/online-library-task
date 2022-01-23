package com.schwarz.onlinelibrary.service;

import com.schwarz.onlinelibrary.exception.InvalidCredentialsException;
import com.schwarz.onlinelibrary.model.LoginDto;
import com.schwarz.onlinelibrary.security.TokenGenerator;
import com.schwarz.onlinelibrary.security.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public String login(LoginDto loginDto) {
        return customerService.findByUsernameAndPassword(loginDto)
                .map(customer -> generateToken())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials. Unable to login"));
    }

    private String generateToken() {
        String token = TokenGenerator.generateNewToken();
        tokenStore.saveToken(token);
        return token;
    }
}
