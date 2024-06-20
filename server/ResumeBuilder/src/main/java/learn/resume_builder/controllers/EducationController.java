package learn.resume_builder.controllers;

import learn.resume_builder.domain.EducationService;
import learn.resume_builder.domain.Result;
import learn.resume_builder.models.Education;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/education")
public class EducationController {

    EducationService service;

    public EducationController (EducationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEducationeById(@PathVariable int id){
        Education education = service.findById(id);
        if (education == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(education);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Education education){
        if(id!= education.getEducationId()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Result<Education> result = service.update(education);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Education education){
        Result<Education> result = service.add(education);
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
