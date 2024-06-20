package learn.resume_builder.controllers;

import learn.resume_builder.domain.ExperienceService;
import learn.resume_builder.domain.Result;
import learn.resume_builder.models.Experience;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/experience")
public class ExperienceController {
    ExperienceService service;

    public ExperienceController (ExperienceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getExperienceById(@PathVariable int id){
        Experience experience = service.findById(id);
        if (experience == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(experience);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Experience experience){
        if(id!= experience.getExperienceId()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Result<Experience> result = service.update(experience);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Experience experience){
        Result<Experience> result = service.add(experience);
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
