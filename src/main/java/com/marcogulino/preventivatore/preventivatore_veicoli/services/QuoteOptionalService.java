package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.QuoteOptional;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.QuoteOptionalRepo;

@Service
public class QuoteOptionalService {

    @Autowired
    private QuoteOptionalRepo quoteOptionalRepo;

    // Explanation: Check if Quote Optional exists
    public Boolean existById(int id) {
        return quoteOptionalRepo.existsById(id);
    }

    // Explanation: Show every Quote Optionals
    public List<QuoteOptional> findAll() {
        return quoteOptionalRepo.findAll();
    }

    // Explanation: Show Quote Optional by id
    public Optional<QuoteOptional> findById(int id) {
        return quoteOptionalRepo.findById(id);
    }

    // Explanation: Create a new Quote Optional
    public QuoteOptional create(QuoteOptional newQuote) {
        return quoteOptionalRepo.save(newQuote);
    }

    // Explanation: Delete a Quote Optional by ID
    public void deleteById(int id) {
        quoteOptionalRepo.deleteById(id);
    }

    // UPDATE
    public Optional<QuoteOptional> update(int id, QuoteOptional edited) {
        return quoteOptionalRepo.findById(id).map(existing -> {

            if (edited.getPriceAtTime() != null) {
                existing.setPriceAtTime(edited.getPriceAtTime());
            }

            if (edited.getOptionalFeature() != null) {
                existing.setOptionalFeature(edited.getOptionalFeature());
            }

            return quoteOptionalRepo.save(existing);
        });
    }

    // Explanation: Save a Quote Optional
    public QuoteOptional save(QuoteOptional editedQuote) {
        return quoteOptionalRepo.save(editedQuote);
    }
}
