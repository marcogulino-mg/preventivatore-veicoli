package com.marcogulino.preventivatore.preventivatore_veicoli.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Vehicle;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.VehicleService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleApiController {

    @Autowired
    private VehicleService vehicleService;

    // INFO: READ
    @GetMapping
    public List<Vehicle> index() {
        List<Vehicle> vehicles = vehicleService.findAll();
        return vehicles;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> show(@PathVariable int id) {
        Optional<Vehicle> vcAttempt = vehicleService.findById(id);
        if (vcAttempt.isEmpty()) {
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Vehicle>(vcAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<Vehicle> store(@Valid @RequestBody Vehicle newVehicle) {
        vehicleService.create(newVehicle);
        return new ResponseEntity<Vehicle>(newVehicle, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> putMethodName(@PathVariable int id, @Valid @RequestBody Vehicle editedVehicle) {
        return vehicleService.update(id, editedVehicle)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> delete(@PathVariable int id) {
        if (vehicleService.existById(id)) {
            vehicleService.deleteById(id);
            return new ResponseEntity<Vehicle>(HttpStatus.OK);
        }

        return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
    }

}
