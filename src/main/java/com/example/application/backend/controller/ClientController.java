package com.example.application.backend.controller;

import com.example.application.backend.exceptions.ClientNotFoundException;
import com.example.application.backend.models.Client;
import com.example.application.backend.models.ClientDTO;
import com.example.application.backend.services.ClientServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //return response in Json format
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServiceInt service;

    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> findAllClients(){
        List<ClientDTO> clients = service.findAll();
        if (clients.isEmpty()){
            return new ResponseEntity<>(clients, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity addNewClient(@Valid @RequestBody Client client){
        service.newClient(client);
        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    @DeleteMapping("/delete/{id_client}")
    public ResponseEntity deleteClient(@PathVariable Long id_client){

        service.deleteClient(id_client);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id_client}")
    public ResponseEntity updateClient(@Valid @PathVariable Long id_client, @RequestBody Client client){

        service.updateClient(id_client, client);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/client/{id_client}")
    public Client findClientById(@PathVariable Long id_client){

        return service.findClientById(id_client);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity deleteAll(){
        service.deleteAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/client/{lastname}")
    public ResponseEntity<ClientDTO> findClientByLastname(@PathVariable String lastname) throws ClientNotFoundException {
        Optional<ClientDTO> optionalClient = service.findClientByLastname(lastname);
        if (optionalClient.isPresent()){
            ClientDTO client = optionalClient.get();
            return new ResponseEntity<>(client, HttpStatus.OK);
        }else{
            throw new ClientNotFoundException("Client not found with lastname " + lastname);
        }
    }

    @GetMapping("/size")
    public ResponseEntity<Long> clientsSize(){
        Long size = service.clientsSize();
        if (size.describeConstable().isEmpty()){
            return new ResponseEntity<>(size, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

}

