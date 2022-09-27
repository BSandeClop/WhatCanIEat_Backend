package net.whatcanieat.FoodApi.security;

import net.whatcanieat.FoodApi.dto.AuthRequest;
import net.whatcanieat.FoodApi.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.AuthenticationException;
@CrossOrigin(origins = "*")
@Controller
public class AuthController {

    @PostMapping(path = "/auth")
    public ResponseEntity<AuthResponse> getJwt(@RequestBody AuthRequest request){
        try {
            return new ResponseEntity<>(JwtUtils.authenticate(request), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            System.out.println("Error en AuthController > getJwt " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
