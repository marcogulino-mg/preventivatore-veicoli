package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Brand;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.BrandRepo;

@Service
public class BrandService {
    @Autowired
    private BrandRepo brandRepo;

    // Explanation: Check if Brand exists
    public Boolean existById(int id) {
        return brandRepo.existsById(id);
    }

    // Explanation: Show every Brands
    public List<Brand> findAll() {
        return brandRepo.findAll();
    }

    // Explanation: Show Brand by id
    public Optional<Brand> findById(int id) {
        return brandRepo.findById(id);
    }

    // Explanation: Create a new Brand
    public Brand create(Brand newBrand) {
        return brandRepo.save(newBrand);
    }

    // Explanation: Delete a Brand by ID
    public void deleteById(int id) {
        brandRepo.deleteById(id);
    }

    // Explanation: Edit a Brand
    public Optional<Brand> update(int id, Brand editedBrand) {
        return brandRepo.findById(id).map(existing -> {

            if (editedBrand.getLogoUrl() != null) {
                existing.setLogoUrl(editedBrand.getLogoUrl());
            }

            if (editedBrand.getName() != null) {
                existing.setName(editedBrand.getName());
            }

            if (editedBrand.getWebsite() != null) {
                existing.setWebsite(editedBrand.getWebsite());
            }

            return brandRepo.save(existing);
        });
    }

    // Explanation: Save a Brand
    public Brand save(Brand editedBrand) {
        return brandRepo.save(editedBrand);
    }

}
