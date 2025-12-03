package com.marcogulino.preventivatore.preventivatore_veicoli.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.OptionalFeature;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.OptionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/optionals")
public class OptionalApiController {

    @Autowired
    private OptionalService optionalService;

    // INFO: READ
    @GetMapping
    public List<OptionalFeature> index() {
        List<OptionalFeature> optionals = optionalService.findAll();
        return optionals;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<OptionalFeature> show(@PathVariable int id) {
        Optional<OptionalFeature> vcAttempt = optionalService.findById(id);
        if (vcAttempt.isEmpty()) {
            return new ResponseEntity<OptionalFeature>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<OptionalFeature>(vcAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<OptionalFeature> store(@Valid @RequestBody OptionalFeature newOptional) {
        optionalService.create(newOptional);
        return new ResponseEntity<OptionalFeature>(newOptional, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<OptionalFeature> putMethodName(@PathVariable int id,
            @Valid @RequestBody OptionalFeature editedOptional) {
        return optionalService.update(id, editedOptional)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<OptionalFeature> delete(@PathVariable int id) {
        if (optionalService.existById(id)) {
            optionalService.deleteById(id);
            return new ResponseEntity<OptionalFeature>(HttpStatus.OK);
        }

        return new ResponseEntity<OptionalFeature>(HttpStatus.NOT_FOUND);
    }
}
