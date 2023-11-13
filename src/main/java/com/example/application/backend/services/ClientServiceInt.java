package com.example.application.backend.services;

import com.example.application.backend.exceptions.ClientNotFoundException;
import com.example.application.backend.models.Client;
import com.example.application.backend.models.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInt {

    List<ClientDTO> findAll();
    void newClient(Client client);
    void deleteClient(Long id_client);
    void updateClient(Long id_client, Client client);
    Client findClientById(Long id_client);
    void deleteAll();
    Optional<ClientDTO> findClientByLastname(String lastname) throws ClientNotFoundException;
    long clientsSize();
    List<ClientDTO> searchFromService(String filterText);


}
