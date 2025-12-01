package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Vehicle;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.VehicleRepo;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    // Explanation: Check if Vehicle exists
    public Boolean existById(int id) {
        return vehicleRepo.existsById(id);
    }

    // Explanation: Show every Vehicles
    public List<Vehicle> findAll() {
        return vehicleRepo.findAll();
    }

    // Explanation: Show Vehicle by id
    public Optional<Vehicle> findById(int id) {
        return vehicleRepo.findById(id);
    }

    // Explanation: Create a new Vehicle
    public Vehicle create(Vehicle newVehicle) {
        return vehicleRepo.save(newVehicle);
    }

    // Explanation: Delete a Vehicle by ID
    public void deleteById(int id) {
        vehicleRepo.deleteById(id);
    }

    // Explanation: Edit a Vehicle
    public Optional<Vehicle> update(int id, Vehicle editedVehicle) {
        return vehicleRepo.findById(id).map(existing -> {

            if (editedVehicle.getModel() != null) {
                existing.setModel(editedVehicle.getModel());
            }

            if (editedVehicle.getYear() != null) {
                existing.setYear(editedVehicle.getYear());
            }

            if (editedVehicle.getEngineCc() != null) {
                existing.setEngineCc(editedVehicle.getEngineCc());
            }

            if (editedVehicle.getFuelType() != null) {
                existing.setFuelType(editedVehicle.getFuelType());
            }

            if (editedVehicle.getStartingPrice() != null) {
                existing.setStartingPrice(editedVehicle.getStartingPrice());
            }

            if (editedVehicle.getDescription() != null) {
                existing.setDescription(editedVehicle.getDescription());
            }

            return vehicleRepo.save(existing);
        });
    }

    // Explanation: Save a Vehicle
    public Vehicle save(Vehicle editedVehicle) {
        return vehicleRepo.save(editedVehicle);
    }

}
