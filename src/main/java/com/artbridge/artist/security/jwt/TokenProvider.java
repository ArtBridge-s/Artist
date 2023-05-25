package com.artbridge.artist.security.jwt;

import org.springframework.security.core.Authentication;

public interface TokenProvider {
    String createToken(Authentication authentication, boolean rememberMe);
    Authentication getAuthentication(String token);
    Long getUserIdFromToken(String token);
    boolean validateToken(String authToken);
}
