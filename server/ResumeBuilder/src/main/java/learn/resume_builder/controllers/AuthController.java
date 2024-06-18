package learn.resume_builder.controllers;

import learn.resume_builder.data.UserJdbcTemplateRepository;
import learn.resume_builder.models.User;
import learn.resume_builder.security.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final UserJdbcTemplateRepository userRepo;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, UserJdbcTemplateRepository userRepo) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")//change this to login
    public ResponseEntity<HashMap<String, String>> authenticate(@RequestBody Map<String, String> credentials) {
        //before last change

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password")));

            if (authentication.isAuthenticated()) {

                User user = userRepo.findByUsername(credentials.get("username"));

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
}
