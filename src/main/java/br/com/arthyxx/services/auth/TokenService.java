package br.com.arthyxx.services.auth;

import br.com.arthyxx.models.Cliente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private static final String SECRET = "mel-api-secret";

    public String generateToken(Cliente cliente){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withIssuer("mel-api")
                .withSubject(cliente.getEmail())
                .withClaim("id", cliente.getId())
                .withClaim("role", cliente.getRole().name())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
    }

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.require(algorithm)
                .withIssuer("mel-api")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));

    }
}
