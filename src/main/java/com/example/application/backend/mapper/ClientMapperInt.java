package com.example.application.backend.mapper;

import com.example.application.backend.models.Client;
import com.example.application.backend.models.ClientDTO;


public interface ClientMapperInt {

    ClientDTO clientToClientDTO(Client client);
    Client clientDTOToClient(ClientDTO clientDTO);

}
