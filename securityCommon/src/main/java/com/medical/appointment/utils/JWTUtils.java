package com.medical.appointment.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Component
public class JWTUtils {

    private final PrivateKey privateKey = parsePrivateKey();

    public String createJwt(String username, String authorities){

        return JWT.create()
                .withExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .withSubject(username)
                .withClaim("roles",authorities)
                .sign(Algorithm.RSA256(null,(RSAPrivateKey) privateKey));
    }

    private static PrivateKey parsePrivateKey() {

        PrivateKey key = null;

        try {

            String path = System.getenv("PRIVATE_KEY");
            String content = Files.readString(Paths.get(path));

            String trimmedContent = content
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            if (!trimmedContent.isEmpty()) {

                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedContent = decoder.decode(trimmedContent);
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedContent);
                KeyFactory factory = KeyFactory.getInstance("rsa");
                key = factory.generatePrivate(keySpec);
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return key;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
