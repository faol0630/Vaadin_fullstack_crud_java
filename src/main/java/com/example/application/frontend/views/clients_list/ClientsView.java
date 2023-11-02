package com.example.application.frontend.views.clients_list;

import com.example.application.backend.controller.ClientController;
import com.example.application.backend.models.Client;
import com.example.application.backend.models.ClientDTO;
import com.example.application.frontend.dialogs.DeleteAllDialog;
import com.example.application.frontend.dialogs.DeleteClientDialog;
import com.example.application.frontend.views.MainLayout;
import com.example.application.frontend.dialogs.EditDialog;
import com.example.application.frontend.views.search_by_lastname.SearchByLastname;
import com.example.application.frontend.views.new_client.NewClientView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Clients")
@Route(value = "clients", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ClientsView extends Div {

    private VerticalLayout verticalLayout;
    public Grid<ClientDTO> client_grid;
    private Button btnGoToAddNewClient;
    private Button btnDelete;
    private Button btnDeleteAll;
    private Button btnUpdate;
    private Button btnSearchByLastname;
    private HorizontalLayout horizontalLayout;
    private EditDialog editDialog;
    private DeleteClientDialog deleteClientDialog;
    private DeleteAllDialog deleteAllDialog;
    private TextField clientsSize;


    //Constructor:
    public ClientsView(ClientController clientController) {

        verticalLayout = new VerticalLayout();
        btnGoToAddNewClient = new Button("New Client");
        client_grid = new Grid<>(ClientDTO.class);
        btnDeleteAll = new Button("Delete All");
        btnSearchByLastname = new Button("Search by lastname");
        horizontalLayout = new HorizontalLayout();
        clientsSize = new TextField("Number of clients:");

        client_grid.setColumns("id_client", "identificationNumber", "name", "lastname");
        client_grid.setWidth("80%");
        client_grid.setItems(clientController.findAllClients().getBody());
        client_grid.setSelectionMode(Grid.SelectionMode.SINGLE); //1)To be able to use in a Dialog

        //Add delete button inside the grid:
        client_grid.addComponentColumn(client -> {

            btnDelete = new Button("Delete");//Create a button in each row
            btnDelete.addClickListener(event -> {

                deleteClientDialog = new DeleteClientDialog(client, clientController, client_grid, clientsSize);
                deleteClientDialog.open();

            });
            return btnDelete;
        }).setHeader("Delete");

        //Add update button inside the grid:
        client_grid.addComponentColumn(client -> {
            btnUpdate = new Button("Update");
            btnUpdate.addClickListener(event ->{

                Client selectedClient = clientController.findClientById(client.getId_client());
                editDialog = new EditDialog(selectedClient, clientController);
                editDialog.open();

                //Update the view after closing the Dialog update: (execute setItems)
                editDialog.addOpenedChangeListener(e ->{
                    if (!e.isOpened()){
                        //if the Dialog is not open:
                        client_grid.setItems(clientController.findAllClients().getBody());
                    }
                });

            });
            return btnUpdate;
        }).setHeader("Update");

        btnGoToAddNewClient.addClickListener(event -> {
            UI.getCurrent().navigate(NewClientView.class);
        });

        btnDeleteAll.addClickListener(event -> {

            deleteAllDialog = new DeleteAllDialog(clientController, client_grid, clientsSize);
            deleteAllDialog.open();

        });

        btnSearchByLastname.addClickListener(e ->{
            UI.getCurrent().navigate(SearchByLastname.class);
        });

        long size = clientController.clientsSize().getBody();
        clientsSize.setValue(String.valueOf(size));

        horizontalLayout.add(btnGoToAddNewClient, btnDeleteAll, btnSearchByLastname);

        verticalLayout.add(horizontalLayout, client_grid, clientsSize);
        verticalLayout.setMargin(true);
        verticalLayout.setPadding(true);

        add(verticalLayout);

    }

}
