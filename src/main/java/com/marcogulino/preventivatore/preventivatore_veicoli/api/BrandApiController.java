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

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Brand;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.BrandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandApiController {
    @Autowired
    private BrandService brandService;

    // INFO: READ
    @GetMapping
    public List<Brand> index() {
        List<Brand> Brands = brandService.findAll();
        return Brands;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<Brand> show(@PathVariable int id) {
        Optional<Brand> vcAttempt = brandService.findById(id);
        if (vcAttempt.isEmpty()) {
            return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Brand>(vcAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<Brand> store(@Valid @RequestBody Brand newBrand) {
        brandService.create(newBrand);
        return new ResponseEntity<Brand>(newBrand, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Brand> putMethodName(@PathVariable int id, @Valid @RequestBody Brand editedBrand) {
        return brandService.update(id, editedBrand)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Brand> delete(@PathVariable int id) {
        if (brandService.existById(id)) {
            brandService.deleteById(id);
            return new ResponseEntity<Brand>(HttpStatus.OK);
        }

        return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
    }
}
