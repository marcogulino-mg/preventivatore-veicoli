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

import com.marcogulino.preventivatore.preventivatore_veicoli.models.VehicleType;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.VehicleTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicle_types")
public class VehicleTypeApiController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    // INFO: READ
    @GetMapping
    public List<VehicleType> index() {
        List<VehicleType> vehicles = vehicleTypeService.findAll();
        return vehicles;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleType> show(@PathVariable int id) {
        Optional<VehicleType> vcAttempt = vehicleTypeService.findById(id);
        if (vcAttempt.isEmpty()) {
            return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<VehicleType>(vcAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<VehicleType> store(@Valid @RequestBody VehicleType newVehicle) {
        vehicleTypeService.create(newVehicle);
        return new ResponseEntity<VehicleType>(newVehicle, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<VehicleType> putMethodName(@PathVariable int id,
            @Valid @RequestBody VehicleType editedVehicle) {
        return vehicleTypeService.update(id, editedVehicle)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleType> delete(@PathVariable int id) {
        if (vehicleTypeService.existById(id)) {
            vehicleTypeService.deleteById(id);
            return new ResponseEntity<VehicleType>(HttpStatus.OK);
        }

        return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
    }
}
