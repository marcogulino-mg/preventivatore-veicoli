package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Quote;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.QuoteRepo;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepo quoteRepo;

    // Explanation: Check if Quote exists
    public Boolean existById(int id) {
        return quoteRepo.existsById(id);
    }

    // Explanation: Show every Quotes
    public List<Quote> findAll() {
        return quoteRepo.findAll();
    }

    // Explanation: Show Quote by id
    public Optional<Quote> findById(int id) {
        return quoteRepo.findById(id);
    }

    // Explanation: Create a new Quote
    public Quote create(Quote newQuote) {
        return quoteRepo.save(newQuote);
    }

    // Explanation: Delete a Quote by ID
    public void deleteById(int id) {
        quoteRepo.deleteById(id);
    }

    // Explanation: Edit a Quote
    public Optional<Quote> update(int id, Quote editedQuote) {
        return quoteRepo.findById(id).map(existing -> {

            if (editedQuote.getBasePriceSnapshot() != null) {
                existing.setBasePriceSnapshot(editedQuote.getBasePriceSnapshot());
            }

            if (editedQuote.getDiscountTotalSnapshot() != null) {
                existing.setDiscountTotalSnapshot(editedQuote.getDiscountTotalSnapshot());
            }

            if (editedQuote.getFinalPrice() != null) {
                existing.setFinalPrice(editedQuote.getFinalPrice());
            }

            if (editedQuote.getNotes() != null) {
                existing.setNotes(editedQuote.getNotes());
            }

            if (editedQuote.getOptionalTotalSnapshot() != null) {
                existing.setOptionalTotalSnapshot(editedQuote.getOptionalTotalSnapshot());
            }

            if (editedQuote.getVehicleEngineCcSnapshot() != null) {
                existing.setVehicleEngineCcSnapshot(editedQuote.getVehicleEngineCcSnapshot());
            }

            if (editedQuote.getVehicleFuelTypeSnapshot() != null) {
                existing.setVehicleFuelTypeSnapshot(editedQuote.getVehicleFuelTypeSnapshot());
            }

            if (editedQuote.getVehicleYearSnapshot() != null) {
                existing.setVehicleYearSnapshot(editedQuote.getVehicleYearSnapshot());
            }

            return quoteRepo.save(existing);
        });
    }

    // Explanation: Save a Quote
    public Quote save(Quote editedQuote) {
        return quoteRepo.save(editedQuote);
    }
}
