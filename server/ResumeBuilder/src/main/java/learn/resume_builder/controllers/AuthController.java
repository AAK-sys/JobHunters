package learn.resume_builder.controllers;

import learn.resume_builder.domain.Result;
import learn.resume_builder.domain.UserService;
import learn.resume_builder.models.User;
import learn.resume_builder.security.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final UserService service;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, UserService service) {
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

        Result<User> result = service.add(credentials);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);

    }

}
