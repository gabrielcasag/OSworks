package com.gabrielcasag.osworks.api.controller;

import com.gabrielcasag.osworks.domain.model.Client;
import com.gabrielcasag.osworks.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> browse(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if ( client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> edit(@Valid @PathVariable Long clientId, @RequestBody Client client) {

        if ( !clientRepository.existsById(clientId) ) {
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);
        client = clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {

        if ( !clientRepository.existsById(clientId) ) {
            return ResponseEntity.notFound().build();
        }

        clientRepository.deleteById(clientId);

        return ResponseEntity.noContent().build();
    }
}
