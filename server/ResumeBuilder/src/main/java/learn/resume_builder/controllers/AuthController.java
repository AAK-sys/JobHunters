package learn.resume_builder.controllers;

import learn.resume_builder.domain.Result;
import learn.resume_builder.domain.UserService;
import learn.resume_builder.models.User;
import learn.resume_builder.security.AppUserService;
import learn.resume_builder.security.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final AppUserService service;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, AppUserService service) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.service = service;
    }

    @PostMapping("/login")//change this to login
    public ResponseEntity<HashMap<String, String>> authenticate(@RequestBody User credentials) {
        //before last change

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

            if (authentication.isAuthenticated()) {

                User user = service.findByUsername(credentials.getUsername());

                String jwtToken = converter.getTokenFromUser(user);

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            System.out.println("Authentication failed: " + ex.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> register (@RequestBody User credentials){
        try {
            String username = credentials.getUsername();
            String password = credentials.getPassword();
            String email = credentials.getEmail();

            User user = service.create(username, password, email);

        } catch (ValidationException ex) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }

        // happy path...
        return new ResponseEntity<>(null, HttpStatus.CREATED);

    }

}
