package com.schwarz.onlinelibrary.security;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenStore {
    private static final Map<String, LocalDateTime> tokens = new HashMap<>();

    /**
     * Saves a token and the time when was generated
     * @param token generated token
     */
    public void saveToken(String token) {
        tokens.put(token, LocalDateTime.now());
    }


    /**
     * Checks if a token is valid.
     * @param token generated token
     * @return true if the token is valid
     */
    public boolean isValidToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        LocalDateTime tokenTime = tokens.get(token);
        if (tokenTime == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(tokenTime, now);
        if (hours > 24) {
            tokens.remove(token);
            return false;
        }
        return true;
    }
}
