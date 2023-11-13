package com.example.application.backend.services;

import com.example.application.backend.dao.ClientRepository;
import com.example.application.backend.exceptions.ClientNotFoundException;
import com.example.application.backend.mapper.ClientMapperInt;
import com.example.application.backend.models.Client;
import com.example.application.backend.models.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientServiceInt {

    private final ClientRepository repo;

    private final ClientMapperInt mapper;

    //constructor:
    public ClientServiceImpl(ClientRepository repo, ClientMapperInt mapper){
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<ClientDTO> findAll() {

        List<Client> clients = repo.findAll();

        List<ClientDTO> dtoClients = clients.stream().map(
                client -> mapper.clientToClientDTO(client)
        ).collect(Collectors.toList());

        return dtoClients;
    }

    @Override
    public void newClient(Client client) {

        if (
                !client.getIdentificationNumber().isEmpty() &&
                        !client.getName().isEmpty() &&
                        !client.getLastname().isEmpty()
        ) {
            repo.save(client);

        }

    }

    @Override
    public void deleteClient(Long id_client) {
        //First we look for the Client:
        Client client = repo.findById(id_client).orElse(null);

        if (client != null) {
            repo.deleteById(id_client);

        }
    }

    @Override
    public void updateClient(Long id_client, Client client) {

        Client clientEntity = repo.findById(id_client).orElse(null);

        if (
                !client.getName().isEmpty() &&
                        !client.getLastname().isEmpty() &&
                        !client.getIdentificationNumber().isEmpty()
        ) {

            assert clientEntity != null;

            clientEntity.setName(client.getName());
            clientEntity.setLastname(client.getLastname());
            clientEntity.setIdentificationNumber(client.getIdentificationNumber());
            repo.save(clientEntity);

        }


    }

    @Override
    public Client findClientById(Long id_client) {
        return repo.findById(id_client).orElse(null);
    }

    @Override
    public void deleteAll() {
        //We verify that the list is not empty:
        List<Client> clients = repo.findAll();

        //If the list is not empty, we delete
        if (clients.size() > 0) {
            repo.deleteAll();

        }
    }

    @Override
    public Optional<ClientDTO> findClientByLastname(String lastname) throws ClientNotFoundException {
        Optional<Client> client = repo.findClientByLastname(lastname);
        if (!client.isPresent()){
            throw new ClientNotFoundException("Client not found");
        }
        ClientDTO clientDTO = mapper.clientToClientDTO(client.get());

        return Optional.ofNullable(clientDTO);

    }

    @Override
    public long clientsSize() {
        return repo.count();
    }

    @Override
    public List<ClientDTO> searchFromService(String filterText) {

        if (filterText == null || filterText.isEmpty()){

            return findAll(); //call to the method located in this service

        }else{
            List<Client> clients =  repo.searchFromRepo(filterText);

            List<ClientDTO> dtoClients = clients.stream().map(
                    client -> mapper.clientToClientDTO(client)
            ).collect(Collectors.toList());

            return dtoClients;
        }
    }

}
