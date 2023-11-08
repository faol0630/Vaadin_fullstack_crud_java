package com.example.application.frontend.dialogs;

import com.example.application.backend.models.ClientDTO;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ClientByLastnameDialog extends Dialog {

    VerticalLayout verticalLayout;
    private final TextField identityNumber;
    private final TextField client_name;
    private final TextField client_lastname;
    private final Button btn_close;

    public ClientByLastnameDialog(ClientDTO clientDTO){

        verticalLayout = new VerticalLayout();
        identityNumber = new TextField();
        client_name = new TextField();
        client_lastname = new TextField();
        btn_close = new Button("Close", event -> close());
        btn_close.addClickShortcut(Key.ENTER);

        identityNumber.setValue(clientDTO.getIdentificationNumber());
        client_name.setValue(clientDTO.getName());
        client_lastname.setValue(clientDTO.getLastname());


        verticalLayout.add(identityNumber, client_name, client_lastname, btn_close);
        verticalLayout.setMargin(true);
        verticalLayout.setPadding(true);
        verticalLayout.setSpacing(true);

        add(verticalLayout);

    }
}
