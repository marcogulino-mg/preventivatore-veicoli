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

import com.marcogulino.preventivatore.preventivatore_veicoli.models.QuoteOptional;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.QuoteOptionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quote_optionals")
public class QuoteOptionalApiController {

    @Autowired
    private QuoteOptionalService quoteOptionalService;

    // INFO: READ
    @GetMapping
    public List<QuoteOptional> index() {
        List<QuoteOptional> quoteOptionals = quoteOptionalService.findAll();
        return quoteOptionals;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<QuoteOptional> show(@PathVariable int id) {
        Optional<QuoteOptional> quoteOpAttempt = quoteOptionalService.findById(id);
        if (quoteOpAttempt.isEmpty()) {
            return new ResponseEntity<QuoteOptional>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<QuoteOptional>(quoteOpAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<QuoteOptional> store(@Valid @RequestBody QuoteOptional newQuote) {
        quoteOptionalService.create(newQuote);
        return new ResponseEntity<QuoteOptional>(newQuote, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<QuoteOptional> putMethodName(@PathVariable int id,
            @Valid @RequestBody QuoteOptional editedQuote) {
        return quoteOptionalService.update(id, editedQuote)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<QuoteOptional> delete(@PathVariable int id) {
        if (quoteOptionalService.existById(id)) {
            quoteOptionalService.deleteById(id);
            return new ResponseEntity<QuoteOptional>(HttpStatus.OK);
        }

        return new ResponseEntity<QuoteOptional>(HttpStatus.NOT_FOUND);
    }
}
