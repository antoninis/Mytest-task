package com.haulmont.testtask.ui.subs;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.service.DoctorService;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

import java.util.List;

public class DoctorSub extends Window {

    private TextField firstName = new TextField("Имя");
    private TextField lastName = new TextField("Фамилия");
    private TextField patronymic = new TextField("Отчество");
    private TextField specialization = new TextField("Специализация");
    private Button submitButton = new Button("Принять");
    private Button closeButton = new Button("Закрыть");
    private Binder<Doctor> binder = new Binder<>(Doctor.class);

    public DoctorSub(Grid<Doctor> grid) {
        setCaption("Добавление врача");
        setWidth("500px");
        setHeight("500px");
        setModal(true);
        setResizable(false);
        center();

        Sub();
        SetButtons(grid);
    }

    private void Sub(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        firstName.setPlaceholder("Введите имя");
        lastName.setPlaceholder("Введите фамилию");
        patronymic.setPlaceholder("Введите отчество");
        specialization.setPlaceholder("Введите специализацию");
        firstName.setWidth("90%");
        lastName.setWidth("90%");
        patronymic.setWidth("90%");
        specialization.setWidth("90%");
        binder.forField(firstName)
                .bind(Doctor::getFirstName, Doctor::setFirstName);
        binder.forField(lastName)
                .bind(Doctor::getLastName, Doctor::setLastName);
        binder.forField(patronymic)
                .bind(Doctor::getPatronymic,Doctor::setPatronymic);
        binder.forField(specialization)
                .bind(Doctor::getSpecialization,Doctor::setSpecialization);
        mainLayout.addComponents(firstName,lastName,patronymic,specialization);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(submitButton,closeButton);
        mainLayout.addComponent(buttons);

        setClosable(false);
        setContent(mainLayout);
    }

    public void SetButtons(Grid<Doctor> grid){
        submitButton.addClickListener(clickEvent -> {
            if (binder.isValid()) {
                Doctor doc = new Doctor();
                doc.setFirstName(firstName.getValue());
                doc.setLastName(lastName.getValue());
                doc.setPatronymic(patronymic.getValue());
                doc.setSpecialization(specialization.getValue());
                DoctorService.save(doc);

                List<Doctor> doctors = DoctorService.getAll();
                grid.setItems(doctors);
                close();
            } else {
                Notification.show("Заполните форму");
            }
        });

        closeButton.addClickListener(clickEvent -> close());
    }
}
