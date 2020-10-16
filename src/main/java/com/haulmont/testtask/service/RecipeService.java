package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.dao.DaoRecipe;
import com.haulmont.testtask.backend.dao.RecipeDao;
import com.haulmont.testtask.backend.entity.Recipe;
import java.util.List;

public class RecipeService {
    private final static DaoRecipe Dao = new RecipeDao();

    public static List<Recipe> getAll() {
        return Dao.getAll();
    }

    public void delete(Recipe recipe) {
        Dao.delete(recipe);
    }

    public static void save(Recipe recipe) { Dao.save(recipe);}

    public static void change(Recipe recipe) { Dao.change(recipe);}

}
