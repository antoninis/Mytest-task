package com.haulmont.testtask.ui;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.backend.entity.Recipe;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.RecipeService;
import com.haulmont.testtask.ui.subs.DoctorSub;
import com.haulmont.testtask.ui.subs.DoctorSubChange;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

import java.util.List;

public class DoctorView extends VerticalLayout implements View {

    private static DoctorService doctorService = new DoctorService();
    public static final String NAME = "doctors";
    private Grid<Doctor> doctorGrid = new Grid<>(Doctor.class);

    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Изменить");
    private Button deleteButton = new Button("Удалить");
    private int i;

    public DoctorView() {
        setDoctorView();
        setButtons();
        updateGrid();
    }

    private void setDoctorView() {
        doctorGrid.removeAllColumns();
        doctorGrid.addColumn(Doctor::getFirstName).setCaption("Имя");
        doctorGrid.addColumn(Doctor::getLastName).setCaption("Фамилия");
        doctorGrid.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        doctorGrid.addColumn(Doctor::getSpecialization).setCaption("Специальность");
        doctorGrid.addColumn(Doctor ->{
            List<Recipe> recipes = RecipeService.getAll();
            i=0;
            for (Recipe r:recipes){
                if (r.getDoctor().getId()==Doctor.getId()){
                    i++;
                }
            }
            return i;
        }).setCaption("Рецептов");
        doctorGrid.setSizeFull();
        setMargin(true);
        setSpacing(true);
        setSizeFull();


        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(addButton,editButton,deleteButton);
        Label a = new Label ("Перед удалением убедитесь что у Врача отсутствуют рецепты");

        addComponents(doctorGrid,buttonsLayout,a);
        setExpandRatio(doctorGrid, 1f);
    }

    private void updateGrid() {
        doctorGrid.setItems(doctorService.getAll());
    }

    private void setButtons(){
        addButton.addClickListener(clickEvent -> getUI().addWindow(new DoctorSub(doctorGrid)));
        editButton.addClickListener(clickEvent -> {
            if (doctorGrid.asSingleSelect().isEmpty()) {Notification.show("Запись не выбрана").setDelayMsec(1000);}
            else {getUI().addWindow(new DoctorSubChange(doctorGrid));}
        });
        deleteButton.addClickListener(clickEvent -> {
           if (doctorGrid.asSingleSelect().isEmpty()) {Notification.show("Запись не выбрана").setDelayMsec(1000);}
           else {
               try {
                   doctorService.delete(doctorGrid.asSingleSelect().getValue());
               }
               catch (Exception e) {
                   //
                   Notification.show("Невозможно удалить, у Врача есть рецепты").setDelayMsec(1000);
               }
           }
           updateGrid();
        });

    }
}
