package com.haulmont.testtask.ui;

import com.haulmont.testtask.backend.entity.Recipe;
import com.haulmont.testtask.service.RecipeService;
import com.haulmont.testtask.ui.subs.RecipeSub;
import com.haulmont.testtask.ui.subs.RecipeSubChange;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class RecipeView extends VerticalLayout implements View {

    private static RecipeService recipeService = new RecipeService();
    public static final String NAME = "recipes";
    private Grid<Recipe> recipeGrid = new Grid<>(Recipe.class);
    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Изменить");
    private Button deleteButton = new Button("Удалить");

    public RecipeView() {
        buildRecipeView();
        setButtons();
        updateGrid();
    }

    private void buildRecipeView() {
        recipeGrid.removeAllColumns();
        recipeGrid.addColumn(Recipe -> Recipe.getDoctor().getFirstName()+ " "
                +Recipe.getDoctor().getLastName() + " - " +Recipe.getDoctor().getSpecialization()).setCaption("Врач");
        recipeGrid.addColumn(Recipe -> Recipe.getPatient().getFirstName()+ " "
                +Recipe.getPatient().getLastName()).setCaption("Пациент");
        recipeGrid.addColumn(Recipe::getDescription).setCaption("Описание");
        recipeGrid.addColumn(Recipe::getCreationDate).setCaption("Дата создания");
        recipeGrid.addColumn(Recipe::getExpirationDate).setCaption("Срок действия");
        recipeGrid.addColumn(Recipe::getPriority).setCaption("Приоритет");
        recipeGrid.setSizeFull();
        setMargin(true);
        setSpacing(true);
        setSizeFull();

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(addButton, editButton, deleteButton);

        addComponents(recipeGrid,buttonsLayout);
        setExpandRatio(recipeGrid, 1f);
    }

    private void updateGrid() {
        recipeGrid.setItems(recipeService.getAll());
    }

    private void setButtons(){
        addButton.addClickListener(clickEvent -> getUI().addWindow(new RecipeSub(recipeGrid)));
        editButton.addClickListener(clickEvent ->{
            if (recipeGrid.asSingleSelect().isEmpty()) {Notification.show("Запись не выбрана").setDelayMsec(1000);}
            else {getUI().addWindow(new RecipeSubChange(recipeGrid));}
        });
        deleteButton.addClickListener(clickEvent -> {
            if (recipeGrid.asSingleSelect().isEmpty()) {
                Notification.show("Запись не выбрана").setDelayMsec(1000);}
            else {
                recipeService.delete(recipeGrid.asSingleSelect().getValue());
            }
            updateGrid();
        });
    }
}
