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
import com.vaadin.flow.component.button.ButtonVariant;
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
public class ClientsView extends VerticalLayout {

    public Grid<ClientDTO> client_grid = new Grid<>(ClientDTO.class);
    private Button btnGoToAddNewClient = new Button("New Client");
    private Button btnDelete;
    private Button btnDeleteAll = new Button("Delete All");
    private Button btnUpdate;
    private Button btnSearchByLastname = new Button("Search by lastname");
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private EditDialog editDialog;
    private DeleteClientDialog deleteClientDialog;
    private DeleteAllDialog deleteAllDialog;
    private TextField clientsSize = new TextField("Number of clients:");;

    //Constructor:
    public ClientsView(ClientController clientController) {

        btnGoToAddNewClientConfigure();

        btnDeleteAllConfigure(clientController);

        btnSearchByLastnameConfigure();

        horizontalLayout.add(btnGoToAddNewClient, btnDeleteAll, btnSearchByLastname);

        clientGridConfigure(clientController);

        clientsSizeConfigure(clientController);

        setSpacing(true);
        setMargin(true);
        setPadding(true);
        setSizeFull();

        add(horizontalLayout, client_grid, clientsSize);

    }

    private void clientsSizeConfigure(ClientController clientController) {

        long size = clientController.clientsSize().getBody();
        clientsSize.setValue(String.valueOf(size));

    }

    private void btnSearchByLastnameConfigure() {

        btnSearchByLastname.addClickListener(event -> UI.getCurrent().navigate(SearchByLastname.class));
        btnSearchByLastname.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

    }

    private void btnGoToAddNewClientConfigure() {

        btnGoToAddNewClient.addClickListener(event -> UI.getCurrent().navigate(NewClientView.class));
        btnGoToAddNewClient.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    }

    private void btnDeleteAllConfigure(ClientController clientController) {

        btnDeleteAll.addClickListener( event -> {
            deleteAllDialog = new DeleteAllDialog(clientController, client_grid, clientsSize);
            deleteAllDialog.open();
        });
        btnDeleteAll.addThemeVariants(ButtonVariant.LUMO_ERROR);

    }

    private void clientGridConfigure(ClientController clientController) {

        client_grid.setColumns("id_client", "identificationNumber", "name", "lastname");
        client_grid.setWidthFull();
        client_grid.setItems(clientController.findAllClients().getBody());
        client_grid.setSelectionMode(Grid.SelectionMode.SINGLE); //1)To be able to use in a Dialog

        //Add delete button inside the grid:
        client_grid.addComponentColumn(client -> {

            btnDelete = new Button("Delete", event -> {
                deleteClientDialog = new DeleteClientDialog(client, clientController, client_grid, clientsSize);
                deleteClientDialog.open();
            });//Create a button in each row
            btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);

            return btnDelete;
        }).setHeader("Delete");

        //Add update button inside the grid:
        client_grid.addComponentColumn(client -> {
            btnUpdate = new Button("Update", event -> {
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
            btnUpdate.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

            return btnUpdate;
        }).setHeader("Update");

        client_grid.getColumns().forEach(column -> column.setAutoWidth(true));

    }

}
