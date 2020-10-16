package com.haulmont.testtask;

import com.haulmont.testtask.ui.DoctorView;
import com.haulmont.testtask.ui.PatientView;
import com.haulmont.testtask.ui.RecipeView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);

        HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setMargin(true);
        menuLayout.setSpacing(true);

        Button patientButton = new Button("Пациенты", clickEvent -> getUI().getNavigator().navigateTo(PatientView.NAME));
        patientButton.setHeight("100%");
        Button doctorButton = new Button("Врачи", clickEvent -> getUI().getNavigator().navigateTo(DoctorView.NAME));
        doctorButton.setHeight("100%");
        Button recipeButton = new Button("Рецепты", clickEvent -> getUI().getNavigator().navigateTo(RecipeView.NAME));
        doctorButton.setHeight("100%");
        menuLayout.addComponents(patientButton, doctorButton, recipeButton);


        VerticalLayout viewsLayout = new VerticalLayout();
        viewsLayout.setSizeFull();
        viewsLayout.setMargin(false);
        viewsLayout.setSpacing(true);

        mainLayout.addComponents(menuLayout, viewsLayout);
        mainLayout.setExpandRatio(viewsLayout, 1f);

        ViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(viewsLayout);
        Navigator navigator = new Navigator(this, viewDisplay);
        navigator.addView(PatientView.NAME, new PatientView());
        navigator.addView(DoctorView.NAME, new DoctorView());
        navigator.addView(RecipeView.NAME, new RecipeView());

        setContent(mainLayout);
    }

}