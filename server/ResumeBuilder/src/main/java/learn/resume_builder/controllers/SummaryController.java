package learn.resume_builder.controllers;

import learn.resume_builder.domain.Result;
import learn.resume_builder.domain.SummaryService;
import learn.resume_builder.models.Summary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/summary")
public class SummaryController {

    SummaryService service;

    public SummaryController (SummaryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSummaryById(@PathVariable int id){
        Summary summary = service.findById(id);
        if (summary == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(summary);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Summary summary){
        if(id!= summary.getSummaryId()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Result<Summary> result = service.update(summary);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Summary summary){
        Result<Summary> result = service.add(summary);
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
