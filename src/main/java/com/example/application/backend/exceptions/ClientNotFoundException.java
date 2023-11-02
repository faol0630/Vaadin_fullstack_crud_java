package com.example.application.backend.exceptions;

public class ClientNotFoundException extends Exception{ //1) extends Exception

    //2) click derecho, generar constructor y escoger segunda opcion(solo con message):
    public ClientNotFoundException(String message) {
        super(message);
    }
}
