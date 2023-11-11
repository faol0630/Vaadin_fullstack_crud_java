package com.example.application.frontend.dialogs;

import com.example.application.backend.controller.ClientController;
import com.example.application.backend.models.ClientDTO;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class DeleteAllDialog extends Dialog {

    HorizontalLayout horizontalLayoutText;
    HorizontalLayout horizontalLayoutButtons;
    VerticalLayout verticalLayout;
    Button btnYes;
    Button btnNo;
    Text question;

    public DeleteAllDialog(ClientController clientController, Grid<ClientDTO> client_grid, TextField clientsSize){

        horizontalLayoutText = new HorizontalLayout();
        horizontalLayoutButtons = new HorizontalLayout();
        verticalLayout = new VerticalLayout();
        btnYes = new Button("Yes", event -> deleteAll(clientController, client_grid, clientsSize));
        btnYes.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        btnNo = new Button("No", event -> close());
        btnNo.addThemeVariants(ButtonVariant.LUMO_ERROR);
        question = new Text("Are you sure to delete all clients? ");

        horizontalLayoutText.add(question);
        horizontalLayoutButtons.add(btnYes, btnNo);
        verticalLayout.add(horizontalLayoutText, horizontalLayoutButtons);
        verticalLayout.setMargin(true);
        verticalLayout.setPadding(true);
        verticalLayout.setSpacing(true);

        add(verticalLayout);

    }

    public void deleteAll(ClientController clientController,Grid<ClientDTO> client_grid, TextField clientsSize){

        clientController.deleteAll();
        client_grid.setItems( clientController.findAllClients().getBody());
        long size = clientController.clientsSize().getBody();
        clientsSize.setValue(String.valueOf(size));
        close();

    }

}
