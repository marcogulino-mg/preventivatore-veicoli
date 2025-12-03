package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.OptionalFeature;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.OptionalRepo;

@Service
public class OptionalService {

    @Autowired
    private OptionalRepo optionalRepo;

    // Explanation: Check if Vehicle exists
    public Boolean existById(int id) {
        return optionalRepo.existsById(id);
    }

    // Explanation: Show every Vehicles
    public List<OptionalFeature> findAll() {
        return optionalRepo.findAll();
    }

    // Explanation: Show Vehicle by id
    public Optional<OptionalFeature> findById(int id) {
        return optionalRepo.findById(id);
    }

    // Explanation: Create a new Vehicle
    public OptionalFeature create(OptionalFeature newVehicle) {
        return optionalRepo.save(newVehicle);
    }

    // Explanation: Delete a Vehicle by ID
    public void deleteById(int id) {
        optionalRepo.deleteById(id);
    }

    // Explanation: Edit a Vehicle
    public Optional<OptionalFeature> update(int id, OptionalFeature editedVehicle) {
        return optionalRepo.findById(id).map(existing -> {

            if (editedVehicle.getName() != null) {
                existing.setName(editedVehicle.getName());
            }

            if (editedVehicle.getPrice() != null) {
                existing.setPrice(editedVehicle.getPrice());
            }

            if (editedVehicle.getDescription() != null) {
                existing.setDescription(editedVehicle.getDescription());
            }

            return optionalRepo.save(existing);
        });
    }

    // Explanation: Save a Vehicle
    public OptionalFeature save(OptionalFeature editedVehicle) {
        return optionalRepo.save(editedVehicle);
    }

}
