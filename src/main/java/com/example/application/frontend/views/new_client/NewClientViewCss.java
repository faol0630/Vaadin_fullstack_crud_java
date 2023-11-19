package com.example.application.frontend.views.new_client;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;

public class NewClientViewCss {

    public static void formLayoutCss(FormLayout formLayout){

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE)
        );
        formLayout.setWidth("50%");
        formLayout.setHeight("60%");
        formLayout.getElement().getStyle().set("margin", "auto");
    }

    public static void newClientBtnCss(Button newClientBtn){
        newClientBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newClientBtn.addClickShortcut(Key.ENTER);
        newClientBtn.getElement().getStyle().set("margin-top", "2em");
    }
}
