package com.schwarz.onlinelibrary.service;

import com.schwarz.onlinelibrary.model.LoginDto;

public interface LoginService {

    /**
     * Receives login information and checks if there is a customer that matches the username and password
     * If the credentials are correct, it will return a generated token
     * @param loginDto credentials
     * @return generated token valid for 24 hours
     */
    String login(LoginDto loginDto);
}
