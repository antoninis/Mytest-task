package com.haulmont.testtask.ui;

import com.haulmont.testtask.backend.entity.Patient;
import com.haulmont.testtask.service.PatientService;
import com.haulmont.testtask.ui.subs.DoctorSub;
import com.haulmont.testtask.ui.subs.PatientSub;
import com.haulmont.testtask.ui.subs.PatientSubChange;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class PatientView extends VerticalLayout implements View {

    private static PatientService patientService = new PatientService();
    public static final String NAME = "";
    private Grid<Patient> patientGrid = new Grid<>(Patient.class);
    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Изменить");
    private Button deleteButton = new Button("Удалить");

    public PatientView() {
        buildPatientView();
        setButtons();
        updateGrid();
    }

    private void buildPatientView() {
        patientGrid.removeAllColumns();
        patientGrid.addColumn(Patient::getFirstName).setCaption("Имя");
        patientGrid.addColumn(Patient::getLastName).setCaption("Фамилия");
        patientGrid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        patientGrid.addColumn(Patient::getPhone).setCaption("Телефон");
        patientGrid.setSizeFull();
        setMargin(true);
        setSpacing(true);
        setSizeFull();

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(addButton,editButton,deleteButton);
        Label a = new Label ("Перед удалением убедитесь что у Пациента отсутствуют рецепты");
        addComponents(patientGrid,buttonsLayout,a);
        setExpandRatio(patientGrid, 1f);
    }

    private void updateGrid() {
        patientGrid.setItems(patientService.getAll());
    }

    private void setButtons(){
        addButton.addClickListener(clickEvent -> getUI().addWindow(new PatientSub(patientGrid)));
        editButton.addClickListener(clickEvent -> {
            if (patientGrid.asSingleSelect().isEmpty()) {Notification.show("Запись не выбрана").setDelayMsec(1000);}
            else {getUI().addWindow(new PatientSubChange(patientGrid));}
        });
        deleteButton.addClickListener(clickEvent -> {
            if (patientGrid.asSingleSelect().isEmpty()) {Notification.show("Запись не выбрана").setDelayMsec(1000);}
            else {
                try {
                    patientService.delete(patientGrid.asSingleSelect().getValue());
                }
                catch (Exception e) {
                    //
                    Notification.show("Невозможно удалить, у Пациента есть рецепты").setDelayMsec(1000);
                }
            }
            updateGrid();
        });
    }
}