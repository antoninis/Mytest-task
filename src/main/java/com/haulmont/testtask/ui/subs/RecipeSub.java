package com.haulmont.testtask.ui.subs;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.backend.entity.Patient;
import com.haulmont.testtask.backend.entity.Recipe;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.PatientService;
import com.haulmont.testtask.service.RecipeService;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.ui.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeSub extends Window {

    private ComboBox<Doctor> doctorComboBox= new ComboBox<>("Врач");
    private ComboBox<Patient> patientComboBox= new ComboBox<>("Пациент");
    private TextField description = new TextField("Описание");
    private DateField creationdatefield = new DateField("Дата создания");
    private DateField expirationdatefield = new DateField("Срок действия");
    private ComboBox<String> priorityComboBox= new ComboBox<>("Приоритет");

    private Button submitButton = new Button("Принять");
    private Button closeButton = new Button("Закрыть");
    private Binder<Recipe> binder = new Binder<>(Recipe.class);

    public RecipeSub(Grid<Recipe> grid) {
        setCaption("Добавление рецепта");
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

        binder.forField(doctorComboBox)
                .bind(Recipe::getDoctor, Recipe::setDoctor);
        binder.forField(patientComboBox)
                .bind(Recipe::getPatient, Recipe::setPatient);
        binder.forField(description)
                .bind(Recipe::getDescription,Recipe::setDescription);
        binder.forField(creationdatefield)
                .withConverter(new LocalDateToDateConverter())
                .bind(Recipe::getCreationDate,Recipe::setCreationDate);
        binder.forField(expirationdatefield)
                .withConverter(new LocalDateToDateConverter())
                .bind(Recipe::getExpirationDate,Recipe::setExpirationDate);
        binder.forField(priorityComboBox)
                .bind(Recipe::getPriority,Recipe::setPriority);

        mainLayout.addComponents(doctorComboBox,patientComboBox,description,
                creationdatefield,expirationdatefield,priorityComboBox);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(submitButton,closeButton);
        mainLayout.addComponent(buttons);

        setClosable(false);
        setContent(mainLayout);
    }

    public void SetButtons(Grid<Recipe> grid){
        properties();


        submitButton.addClickListener(clickEvent -> {
            if (binder.isValid()) {
                Recipe rec = new Recipe();
                rec.setDoctor(doctorComboBox.getValue());
                rec.setPatient(patientComboBox.getValue());
                rec.setDescription(description.getValue());
                rec.setCreationDate(Date.from(creationdatefield.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                rec.setExpirationDate(Date.from(expirationdatefield.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                rec.setPriority(priorityComboBox.getValue());
                RecipeService.save(rec);

                List<Recipe> recipes = RecipeService.getAll();
                grid.setItems(recipes);
                close();
            } else {
                Notification.show("Заполните форму");
            }
        });

        closeButton.addClickListener(clickEvent -> close());
    }

    public void properties(){
        List<Doctor> doctors = DoctorService.getAll();
        List<Patient> patients = PatientService.getAll();
        List<String> priorities = new ArrayList<>();
        priorities.add("Нормальный");
        priorities.add("Срочный");
        priorities.add("Немедленный");
        doctorComboBox.setPlaceholder("Выберите врача");
        patientComboBox.setPlaceholder("Выберите пациента");
        description.setPlaceholder("Введите описание");
        creationdatefield.setPlaceholder("Укажите дату создания");
        expirationdatefield.setPlaceholder("Укажите срок действия");
        priorityComboBox.setPlaceholder("Выберите приоритет");
        doctorComboBox.setWidth("90%");
        patientComboBox.setWidth("90%");
        description.setWidth("90%");
        creationdatefield.setWidth("90%");
        expirationdatefield.setWidth("90%");
        priorityComboBox.setWidth("90%");
        doctorComboBox.setItems(doctors);
        patientComboBox.setItems(patients);
        priorityComboBox.setItems(priorities);
        doctorComboBox.setItemCaptionGenerator(doctor -> doctor.getFirstName()+ " "
                +doctor.getLastName()+ " - " + doctor.getSpecialization());
        patientComboBox.setItemCaptionGenerator(patient -> patient.getFirstName()+ " "
                +patient.getLastName());
    }
}
