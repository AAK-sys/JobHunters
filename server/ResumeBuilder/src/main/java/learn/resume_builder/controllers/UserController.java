package learn.resume_builder.controllers;

import learn.resume_builder.domain.Result;
import learn.resume_builder.domain.UserService;
import learn.resume_builder.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"http://localhost:3000"})
@RequestMapping("/api/user")
public class UserController {

    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id){
        User user = service.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Object> getUserByUsername(@RequestParam(required = true) String name){
        User user = service.findByUsername(name);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody User user){
        if(id != user.getUserId()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Result<User> result = service.update(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        boolean result = service.deleteById(id);
        if (result) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
