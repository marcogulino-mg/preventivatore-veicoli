package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.VehicleType;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.VehicleTypeRepo;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepo vehicleTypeRepo;

    // Explanation: Check if Vehicle Type exists
    public Boolean existById(int id) {
        return vehicleTypeRepo.existsById(id);
    }

    // Explanation: Show every Vehicle Types
    public List<VehicleType> findAll() {
        return vehicleTypeRepo.findAll();
    }

    // Explanation: Show Vehicle Type by id
    public Optional<VehicleType> findById(int id) {
        return vehicleTypeRepo.findById(id);
    }

    // Explanation: Create a new Vehicle Type
    public VehicleType create(VehicleType newVehicle) {
        return vehicleTypeRepo.save(newVehicle);
    }

    // Explanation: Delete a Vehicle Type by ID
    public void deleteById(int id) {
        vehicleTypeRepo.deleteById(id);
    }

    // Explanation: Edit a Vehicle Type
    public Optional<VehicleType> update(int id, VehicleType editedVehicle) {
        return vehicleTypeRepo.findById(id).map(existing -> {

            if (editedVehicle.getLogoUrl() != null) {
                existing.setLogoUrl(editedVehicle.getLogoUrl());
            }

            if (editedVehicle.getName() != null) {
                existing.setName(editedVehicle.getName());
            }

            return vehicleTypeRepo.save(existing);
        });
    }

    // Explanation: Save a Vehicle
    public VehicleType save(VehicleType editedVehicle) {
        return vehicleTypeRepo.save(editedVehicle);
    }

}
