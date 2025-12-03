package com.marcogulino.preventivatore.preventivatore_veicoli.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Client;
import com.marcogulino.preventivatore.preventivatore_veicoli.repositories.ClientRepo;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    // Explanation: Check if Client exists
    public Boolean existById(int id) {
        return clientRepo.existsById(id);
    }

    // Explanation: Show every Clients
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    // Explanation: Show Client by id
    public Optional<Client> findById(int id) {
        return clientRepo.findById(id);
    }

    // Explanation: Create a new Client
    public Client create(Client newClient) {
        return clientRepo.save(newClient);
    }

    // Explanation: Delete a Client by ID
    public void deleteById(int id) {
        clientRepo.deleteById(id);
    }

    // Explanation: Edit a Client
    public Optional<Client> update(int id, Client editedClient) {
        return clientRepo.findById(id).map(existing -> {

            if (editedClient.getFirstname() != null) {
                existing.setFirstname(editedClient.getFirstname());
            }

            if (editedClient.getLastname() != null) {
                existing.setLastname(editedClient.getLastname());
            }

            if (editedClient.getEmail() != null) {
                existing.setEmail(editedClient.getEmail());
            }

            if (editedClient.getPhoneNumber() != null) {
                existing.setPhoneNumber(editedClient.getPhoneNumber());
            }

            if (editedClient.getNotes() != null) {
                existing.setNotes(editedClient.getNotes());
            }

            return clientRepo.save(existing);
        });
    }

    // Explanation: Save a Client
    public Client save(Client editedClient) {
        return clientRepo.save(editedClient);
    }
}
