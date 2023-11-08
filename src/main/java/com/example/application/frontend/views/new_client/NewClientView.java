package com.example.application.frontend.views.new_client;


import com.example.application.backend.controller.ClientController;
import com.example.application.backend.models.Client;
import com.example.application.frontend.views.MainLayout;
import com.example.application.frontend.views.clients_list.ClientsView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("NewClient")
@Route(value = "new_client", layout = MainLayout.class)
public class NewClientView extends Div {

    private final VerticalLayout verticalLayout;
    private final TextField identityNumber;
    private final TextField client_name;
    private final TextField client_lastname;
    private final TextField passwordTF;
    private final Button newClient;

    //Constructor:
    public NewClientView(ClientController clientController){

        verticalLayout = new VerticalLayout();
        identityNumber = new TextField("Your identity number: ");
        client_name = new TextField("Your name: ");
        client_lastname = new TextField("Your lastname: ");
        passwordTF = new TextField("Your password: ");
        newClient = new Button("Add Client", event -> {

            if (
                    !identityNumber.isEmpty() &&
                            !client_name.isEmpty() &&
                            !client_lastname.isEmpty()
            ){
                clientController.addNewClient(addNewClient());
                UI.getCurrent().navigate(ClientsView.class);
                Notification.show("New Client Added");
            }else{
                UI.getCurrent().navigate(ClientsView.class);
                Notification.show("Unadded Client");
            }

        });
        newClient.addClickShortcut(Key.ENTER);

        verticalLayout.add(identityNumber, passwordTF, client_name, client_lastname, newClient);
        verticalLayout.setMargin(true);
        verticalLayout.setPadding(true);
        verticalLayout.setSpacing(true);

        add(verticalLayout);

    }

    public Client addNewClient(){

        String identity = identityNumber.getValue();
        String password = passwordTF.getValue();
        String name = client_name.getValue();
        String lastname = client_lastname.getValue();
        Client newClient = new Client();
        newClient.setIdentificationNumber(identity);
        newClient.setPassword(password);
        newClient.setName(name);
        newClient.setLastname(lastname);

        return newClient;

    }

}
