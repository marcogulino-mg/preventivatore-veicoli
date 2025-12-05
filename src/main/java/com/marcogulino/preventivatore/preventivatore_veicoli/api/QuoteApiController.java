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

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Quote;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.QuoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quotes")
public class QuoteApiController {

    @Autowired
    private QuoteService quoteService;

    // INFO: READ
    @GetMapping
    public List<Quote> index() {
        List<Quote> quotes = quoteService.findAll();
        return quotes;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<Quote> show(@PathVariable int id) {
        Optional<Quote> qtAttempt = quoteService.findById(id);
        if (qtAttempt.isEmpty()) {
            return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Quote>(qtAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<Quote> store(@Valid @RequestBody Quote newQuote) {
        quoteService.create(newQuote);
        return new ResponseEntity<Quote>(newQuote, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Quote> putMethodName(@PathVariable int id, @Valid @RequestBody Quote editedQuote) {
        return quoteService.update(id, editedQuote)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Quote> delete(@PathVariable int id) {
        if (quoteService.existById(id)) {
            quoteService.deleteById(id);
            return new ResponseEntity<Quote>(HttpStatus.OK);
        }

        return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
    }
}
