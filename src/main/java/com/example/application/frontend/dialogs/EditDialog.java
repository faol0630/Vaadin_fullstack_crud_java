package com.example.application.frontend.dialogs;

import com.example.application.backend.controller.ClientController;
import com.example.application.backend.models.Client;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditDialog extends Dialog {

    VerticalLayout verticalLayout;
    private final TextField identityNumber;
    private final TextField client_name;
    private final TextField client_lastname;

    //Constructor:
    public EditDialog(Client client, ClientController clientController){

        verticalLayout = new VerticalLayout();
        identityNumber = new TextField("Update identity number");
        client_name = new TextField("Update your name");
        client_lastname = new TextField("Update your lastname");
        Button btnUpdate = new Button("Update Client");

        //Data in the textField when the Dialog is opened:
        identityNumber.setValue(client.getIdentificationNumber());
        client_name.setValue(client.getName());
        client_lastname.setValue(client.getLastname());

        btnUpdate.addClickListener(event -> {
            updateClient(client, clientController);


        });
        btnUpdate.addClickShortcut(Key.ENTER);

        verticalLayout.add(identityNumber, client_name, client_lastname, btnUpdate);
        verticalLayout.setMargin(true);
        verticalLayout.setPadding(true);
        verticalLayout.setSpacing(true);

        add(verticalLayout);
    }

    public void updateClient2(Client client, ClientController clientController){

        String identifyNumber = identityNumber.getValue();
        String name = client_name.getValue();
        String lastname = client_lastname.getValue();

        Client newClient = new Client();
        newClient.setId_client(client.getId_client());
        newClient.setIdentificationNumber(identifyNumber);
        newClient.setName(name);
        newClient.setLastname(lastname);

        clientController.updateClient(client.getId_client(), newClient);

    }

    public void updateClient(Client client, ClientController clientController){
        if (
                !identityNumber.isEmpty() &&
                        !client_name.isEmpty() &&
                        !client_lastname.isEmpty()
        ){

            updateClient2(client, clientController);
            close();
            Notification.show("Changes made successfully");

        }else{

            close();
            Notification.show("Changes not made");

        }
    }

}
