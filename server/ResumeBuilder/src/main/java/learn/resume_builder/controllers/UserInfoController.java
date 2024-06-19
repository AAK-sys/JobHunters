package learn.resume_builder.controllers;

import learn.resume_builder.domain.Result;
import learn.resume_builder.domain.UserInfoService;
import learn.resume_builder.models.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

    UserInfoService service;

    public UserInfoController(UserInfoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserInfoById(@PathVariable int id){
        UserInfo userInfo = service.findById(id);
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userInfo);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody UserInfo userInfo){
        if(id!= userInfo.getUserInfoId()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Result<UserInfo> result = service.update(userInfo);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserInfo userInfo){
        Result<UserInfo> result = service.add(userInfo);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
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
