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

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Client;
import com.marcogulino.preventivatore.preventivatore_veicoli.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientApiController {

    @Autowired
    private ClientService clientService;

    // INFO: READ
    @GetMapping
    public List<Client> index() {
        List<Client> clients = clientService.findAll();
        return clients;
    }

    // INFO: READ By ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> show(@PathVariable int id) {
        Optional<Client> clAttempt = clientService.findById(id);
        if (clAttempt.isEmpty()) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(clAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<Client> store(@Valid @RequestBody Client newClient) {
        clientService.create(newClient);
        return new ResponseEntity<Client>(newClient, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Client> putMethodName(@PathVariable int id, @Valid @RequestBody Client editedClient) {
        return clientService.update(id, editedClient)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable int id) {
        if (clientService.existById(id)) {
            clientService.deleteById(id);
            return new ResponseEntity<Client>(HttpStatus.OK);
        }

        return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
    }

}
