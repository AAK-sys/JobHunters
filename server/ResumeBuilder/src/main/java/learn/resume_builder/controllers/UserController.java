package learn.resume_builder.controllers;

import learn.resume_builder.App;
import learn.resume_builder.domain.Result;
import learn.resume_builder.models.AppUser;
import learn.resume_builder.models.User;
import learn.resume_builder.security.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getUserById(@PathVariable int id){
//        User user = service.findById(id);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(user);
//    }

    @GetMapping("/all")
    public List<AppUser> getAllUsers() {
        return appUserService.findAll();
    }

    @GetMapping
    public ResponseEntity<Object> getUserByUsername(@RequestParam(required = true) String name){
        UserDetails appUser = appUserService.loadUserByUsername(name);
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appUser);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody User user){
//        if(id != user.getUserId()){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        Result<User> result = service.update(user);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
//        }
//        return ErrorResponse.build(result);
//    }
}
