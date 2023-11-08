package com.example.application.frontend.views.search_by_lastname;

import com.example.application.backend.controller.ClientController;
import com.example.application.backend.exceptions.ClientNotFoundException;
import com.example.application.backend.models.ClientDTO;
import com.example.application.frontend.dialogs.ClientByLastnameDialog;
import com.example.application.frontend.views.MainLayout;
import com.example.application.frontend.views.clients_list.ClientsView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.http.ResponseEntity;

@PageTitle("SearchByLastname")
@Route(value = "search_by_lastname", layout = MainLayout.class)
public class SearchByLastname extends Div {

    private final VerticalLayout verticalLayout;
    private HorizontalLayout horizontalLayout;
    private final TextField client_lastname;
    private final Button searchByLastname;
    private Button goToClientsList;

    //Constructor
    public SearchByLastname(ClientController clientController){

        verticalLayout = new VerticalLayout();
        horizontalLayout = new HorizontalLayout();
        client_lastname = new TextField("Your lastname: ");
        goToClientsList = new Button("Go to client's list", event -> UI.getCurrent().navigate(ClientsView.class));
        searchByLastname = new Button("Search ", event -> {

            String lastname = client_lastname.getValue();

            try {

                searchByLastname(clientController);

            } catch (ClientNotFoundException ex) {

                Notification.show("client with lastname " + lastname + " not found");
                UI.getCurrent().navigate(ClientsView.class);
                throw new RuntimeException(ex.getMessage());

            }

        });
        searchByLastname.addClickShortcut(Key.ENTER);

        horizontalLayout.add(searchByLastname, goToClientsList);

        verticalLayout.add(client_lastname, horizontalLayout);
        verticalLayout.setMargin(true);
        verticalLayout.setPadding(true);
        verticalLayout.setSpacing(true);

        add(verticalLayout);

    }

    public void searchByLastname(ClientController clientController) throws ClientNotFoundException {

        String lastname = client_lastname.getValue();
        ResponseEntity<ClientDTO> clientFounded = clientController.findClientByLastname(lastname);

        if (clientFounded.hasBody()){
            //Open dialog:
            ClientByLastnameDialog clientByLastnameDialog = new ClientByLastnameDialog(clientFounded.getBody());
            client_lastname.setValue("");
            clientByLastnameDialog.open();

        }

    }

}
