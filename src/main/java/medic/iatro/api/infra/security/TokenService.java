package medic.iatro.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import medic.iatro.api.domain.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("{api.security.secret}")
    private String secretKey;
    public  String generateJWT(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
           return  JWT.create()
                    .withIssuer("Iatro")
                   .withSubject(user.getUsername())
                   .withIssuer("Iatro")
                   .withClaim("id", user.getId())
                   .withExpiresAt(generateInstants())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException();
        }
    }
    public  String getSubjet(String token) {

            if (token == null){
                throw  new RuntimeException("Token nulo o no proporcionado");
            }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
           DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("Iatro")
                    .build()
                    .verify(token);
           return verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println( exception.toString());
            throw  new RuntimeException("el token es sulo");
        }


    }
    private Instant generateInstants(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
