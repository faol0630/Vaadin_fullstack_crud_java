package com.example.application.frontend.views.clients_list;

import com.vaadin.flow.component.grid.Grid;

public class ClientsViewCss {

    public static void gridCss(Grid client_grid){

        client_grid.setWidthFull();
        client_grid.getElement().getStyle().setMargin("15px");
        client_grid.getElement().getStyle().set("display", "flex");
        client_grid.getElement().getStyle().set("flex-direction", "column");
    }
}
