package net.whatcanieat.FoodApi.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = JwtUtils.getTokenWithoutBearer(request);
            verifyAndAuthUser(request, token);
        } catch (Exception e){
            System.out.println("Error en JwtRequestFilter > doFilterInternal " + e);
        }

        filterChain.doFilter(request, response);
    }

    private void verifyAndAuthUser(HttpServletRequest request, String token){
        try {
            if(JwtUtils.verifyToken(token)){
                String username = JwtUtils.getUsername(token);
                UserPrincipal principal = new UserPrincipal(username);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e){
            System.out.println("Error en JwtRequestFilter > verifyAndAuthUser " + e);
        }
    }
}
