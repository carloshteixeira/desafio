package com.devsuperior.desafio.controllers;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.desafio.services.UserService;

@RestController
public class AuthController {

    private static final String CLIENT_ID = "myclientid";
    private static final String CLIENT_SECRET = "myclientsecret";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    @PostMapping("/oauth2/token")
    public ResponseEntity<Map<String, Object>> login(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(name = "grant_type") String grantType) {

        if (!"password".equals(grantType)) {
            return ResponseEntity
                    .badRequest()
                    .body(error("unsupported_grant_type"));
        }

        if (!validClient(authorization)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error("invalid_client"));
        }

        UserDetails user;

        try {
            user = userService.loadUserByUsername(username);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error("invalid_grant"));
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error("invalid_grant"));
        }

        Instant now = Instant.now();
        long expiresIn = 86400L;

        List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("dscommerce")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.getUsername())
                .claim("authorities", authorities)
                .build();

        JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        String token = jwtEncoder
                .encode(JwtEncoderParameters.from(header, claims))
                .getTokenValue();

        Map<String, Object> response = new HashMap<>();

        response.put("access_token", token);
        response.put("token_type", "bearer");
        response.put("expires_in", expiresIn);

        return ResponseEntity.ok(response);
    }

    private boolean validClient(String authorization) {

        if (authorization == null || !authorization.startsWith("Basic ")) {
            return false;
        }

        try {
            String encoded = authorization.substring(6);

            String decoded = new String(
                    Base64.getDecoder().decode(encoded),
                    StandardCharsets.UTF_8
            );

            String expected = CLIENT_ID + ":" + CLIENT_SECRET;

            return expected.equals(decoded);
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    private Map<String, Object> error(String message) {

        Map<String, Object> response = new HashMap<>();

        response.put("error", message);

        return response;
    }
}