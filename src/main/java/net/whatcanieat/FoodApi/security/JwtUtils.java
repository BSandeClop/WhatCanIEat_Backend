package net.whatcanieat.FoodApi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.whatcanieat.FoodApi.dto.AuthRequest;
import net.whatcanieat.FoodApi.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

public class JwtUtils {

    private static final long EXPIRATION_TIME = 43200; //12 hs

    @Value("${JWT_SECRET}")
    private static String SECRET;

    @Value("${ADMIN}")
    private static String USER;

    @Value("${PWD}")
    private static String PWD;

    public static AuthResponse authenticate(AuthRequest request) throws AuthenticationException {
        System.out.println("Password is " + PWD);
        System.out.println("User is " + USER);
        try {
            if (request.getUsername().equals(USER) && request.getPassword().equals(PWD)){
                System.out.println("Entre al if");
                UserPrincipal user = new UserPrincipal(request.getUsername());

                Authentication auth = new UsernamePasswordAuthenticationToken(user, null);

                SecurityContextHolder.getContext().setAuthentication(auth);

                return new AuthResponse(createToken(user.getUsername()));
            }
            System.out.println("No entre al if");
            throw new AuthenticationException();
        } catch (Exception e){
            throw e;
        }
    }

    private static String createToken(String username){
        Date now = new Date();

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME*1000))
                .signWith(SignatureAlgorithm.ES256, SECRET)
                .compact();
    }

    public static boolean verifyToken(String token){
        try {
            getClaims(token);
            return true;
        } catch (Exception e){
            System.out.println("Error en JwtUtils > verifyToken " + e);
        }
        return false;
    }

    public static String getUsername(String token){
        final Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private static Claims getClaims(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            System.out.println("Error en JwtUtils > getClaims " + e);
            throw e;
        }
    }

    public static Optional<String> getTokenWithoutBearer(HttpServletRequest request) {
        Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"));
        return getTokenWithoutBearer(token);
    }

    private static Optional<String> getTokenWithoutBearer(Optional<String> bearerToken){
        return bearerToken.map(s -> s.substring(7));
    }
}
