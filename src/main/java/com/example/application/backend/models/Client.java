package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;

    @Column(name = "identityNumber", length = 9, unique = true)
    @NotBlank(message = "Add the Client identification number")
    private String identificationNumber;

    @Column(name = "password", length = 20)
    @NotBlank(message = "Password must not be empty")
    private String password;

    @Column(name = "client_name", length = 40)
    @NotBlank(message = "Add the Client name")
    private String name;

    @Column(name = "client_lastname", length = 40)
    @NotBlank(message = "Add the Client lastname")
    private String lastname;

}
