package com.example.application;

import com.example.application.backend.dao.ClientRepository;
import com.example.application.backend.models.Client;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer {

    //Starting with a non-empty database:

    /*If you want to start with an empty database, change application.properties,
    data.initialization.enabled=true, to false*/
    @Value("${data.initialization.enabled}")
    private boolean dataInitializationEnabled;

    private final ClientRepository repo;

    public DataInitializer(ClientRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void init() {

        if (dataInitializationEnabled) {

            Client client1 = new Client(456347L, "J7587321G", "1234", "John", "Martin");
            Client client2 = new Client(879236L, "K7561123N", "12345", "Kenny", "Joe");
            Client client3 = new Client(923455L, "B6463524F", "123", "Peter", "Black");
            Client client4 = new Client(812654L, "M7583726F", "2345", "Francis", "Lewis");

            Optional<Client> clientOne = repo.findClientByLastname(client1.getLastname());
            Optional<Client> clientTwo = repo.findClientByLastname(client2.getLastname());
            Optional<Client> clientThree = repo.findClientByLastname(client3.getLastname());
            Optional<Client> clientFour = repo.findClientByLastname(client4.getLastname());

            if (clientOne.isEmpty() && clientTwo.isEmpty() && clientThree.isEmpty() && clientFour.isEmpty()) {

                repo.save(client1);
                repo.save(client2);
                repo.save(client3);
                repo.save(client4);

            }
        }

    }
}
