package learn.resume_builder.controllers;


import learn.resume_builder.domain.Result;
import learn.resume_builder.domain.SkillService;
import learn.resume_builder.models.Skill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    SkillService service;

    public SkillController (SkillService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSkillById(@PathVariable int id){
        Skill Skill = service.findById(id);
        if (Skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Skill);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Skill Skill){
        if(id!= Skill.getSkillId()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Result<Skill> result = service.update(Skill);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Skill Skill){
        Result<Skill> result = service.add(Skill);
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
