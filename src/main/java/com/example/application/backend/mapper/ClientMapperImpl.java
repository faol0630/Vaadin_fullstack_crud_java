package com.example.application.backend.mapper;

import com.example.application.backend.models.Client;
import com.example.application.backend.models.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperImpl implements ClientMapperInt{

    @Override
    public ClientDTO clientToClientDTO(Client client) {

        if (client == null){
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId_client(client.getId_client());
        clientDTO.setIdentificationNumber(client.getIdentificationNumber());
        clientDTO.setName(client.getName());
        clientDTO.setLastname(client.getLastname());
        return clientDTO;


    }

    @Override
    public Client clientDTOToClient(ClientDTO clientDTO) {

        if (clientDTO == null){
            return null;
        }
        Client client = new Client();
        client.setIdentificationNumber(clientDTO.getIdentificationNumber());
        client.setPassword("1234");
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        return client;


    }
}
