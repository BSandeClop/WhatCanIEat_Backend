package net.whatcanieat.FoodApi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.whatcanieat.FoodApi.dto.AuthRequest;
import net.whatcanieat.FoodApi.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

public class JwtUtils {

    private static final long EXPIRATION_TIME = 43200; //12 hs

    private static final String SECRET = "${JWT_SECRET}";

    public static AuthResponse authenticate(AuthRequest request) throws AuthenticationException {
        try {
            if (request.getUsername().equals("${ADMIN}") && request.getPassword().equals("${PWD}")){

                UserPrincipal user = new UserPrincipal(request.getUsername());
                Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
                SecurityContextHolder.getContext().setAuthentication(auth);
                
                return new AuthResponse(createToken(user.getUsername()));
            }
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

    public static String getTokenWithoutBearer(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }
}
