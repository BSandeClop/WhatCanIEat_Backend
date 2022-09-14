package net.whatcanieat.FoodApi.security;

import net.whatcanieat.FoodApi.dto.AuthRequest;
import net.whatcanieat.FoodApi.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @PostMapping(path = "/auth")
    public ResponseEntity<AuthResponse> getJwt(@RequestBody AuthRequest request){
        return new ResponseEntity<>(JwtUtils.authenticate(request), HttpStatus.OK);
    }
}
