package com.haulmont.testtask.ui.subs;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.backend.entity.Patient;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.PatientService;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

import java.util.List;

public class PatientSubChange extends Window {

    private TextField firstName = new TextField("Имя");
    private TextField lastName = new TextField("Фамилия");
    private TextField patronymic = new TextField("Отчество");
    private TextField phone = new TextField("Телефон");
    private Button submitButton = new Button("Принять");
    private Button closeButton = new Button("Закрыть");
    private Binder<Patient> binder = new Binder<>(Patient.class);

    public PatientSubChange(Grid<Patient> grid) {
        setCaption("Изменение Пациента");
        setWidth("500px");
        setHeight("500px");
        setModal(true);
        setResizable(false);
        center();

        Sub();
        SetButtons(grid);
    }

    public void Sub(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        firstName.setPlaceholder("Введите имя");
        lastName.setPlaceholder("Введите фамилию");
        patronymic.setPlaceholder("Введите отчество");
        phone.setPlaceholder("Введите телефон");
        firstName.setWidth("90%");
        lastName.setWidth("90%");
        patronymic.setWidth("90%");
        phone.setWidth("90%");
        binder.forField(firstName)
                .bind(Patient::getFirstName, Patient::setFirstName);
        binder.forField(lastName)
                .bind(Patient::getLastName, Patient::setLastName);
        binder.forField(patronymic)
                .bind(Patient::getPatronymic,Patient::setPatronymic);
        binder.forField(phone)
                .bind(Patient::getPhone,Patient::setPhone);
        mainLayout.addComponents(firstName,lastName,patronymic,phone);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(submitButton,closeButton);
        mainLayout.addComponent(buttons);

        setClosable(false);
        setContent(mainLayout);
    }

    public void SetButtons(Grid<Patient> grid){
        Patient patient = grid.asSingleSelect().getValue();
        binder.setBean(patient);
        submitButton.addClickListener(clickEvent -> {
            if (binder.isValid()) {
                PatientService.change(patient);
                List<Patient> patients = PatientService.getAll();
                grid.setItems(patients);
                close();
            } else {
                Notification.show("Заполните форму");
            }
        });

        closeButton.addClickListener(clickEvent -> close());
    }

}
