package com.tsystems.study.jamboree.rest;

import com.tsystems.study.jamboree.model.AuthRequest;
import com.tsystems.study.jamboree.model.AuthResponse;
import com.tsystems.study.jamboree.model.User;
import com.tsystems.study.jamboree.security.JwtTokenService;
import com.tsystems.study.jamboree.service.JamboreeException;
import com.tsystems.study.jamboree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    JwtTokenService jwtTokenService;

    @PostMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authorize(@RequestBody  AuthRequest request) throws JamboreeException {
        authenticate(request.getLogin(), request.getPassword());

        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        User user = userService.findByLogin(request.getLogin());
        String token = jwtTokenService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, user));
    }

    private void authenticate(String username, String password) throws JamboreeException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new JamboreeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new JamboreeException("INVALID_CREDENTIALS", e);
        }
    }
}
