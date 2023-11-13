package com.example.application.backend.dao;

import com.example.application.backend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(
           value = "SELECT * FROM client WHERE client_lastname = :lastname",
            nativeQuery = true
    )
    Optional<Client> findClientByLastname(@Param("lastname")String lastname);

    @Query(
            value = "SELECT * FROM client WHERE client_lastname LIKE CONCAT('%', :searchTerm, '%');",
            nativeQuery = true
    )
    List<Client> searchFromRepo(@Param("searchTerm") String searchTerm);

}
