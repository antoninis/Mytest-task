package com.haulmont.testtask.backend.dao;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.backend.entity.Patient;
import com.haulmont.testtask.backend.entity.Recipe;
import com.haulmont.testtask.service.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao implements DaoRecipe {

    @Override
    public List<Recipe> getAll(){
        List<Recipe> list = new ArrayList<>();
        String sql = "SELECT * FROM recipes;";
        try (Connection connection = DBService.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(rs.getLong("recipe_id"));
                Doctor doctor = new DoctorDao().getById(rs.getLong("doctor_id"));
                recipe.setDoctor(doctor);
                Patient patient = new PatientDao().getById(rs.getLong("patient_id"));
                recipe.setPatient(patient);
                recipe.setDescription(rs.getString("description"));
                recipe.setCreationDate(rs.getDate("creation_date"));
                recipe.setExpirationDate(rs.getDate("expiration_date"));
                recipe.setPriority(rs.getString("priority"));
                list.add(recipe);
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(Recipe recipe){
        String sql = "DELETE FROM recipes WHERE recipe_id = ?;";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,recipe.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Recipe recipe) {
        String sql = "INSERT INTO recipes(doctor_id, patient_id, description, creation_date, expiration_date, priority)" +
                "VALUES(?,?,?,?,?,?)";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,recipe.getDoctor().getId());
            st.setLong(2,recipe.getPatient().getId());
            st.setString(3,recipe.getDescription());
            st.setDate(4, new Date(recipe.getCreationDate().getTime()));
            st.setDate(5, new Date(recipe.getExpirationDate().getTime()));
            st.setString(6,recipe.getPriority());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void change(Recipe recipe) {
        String sql = "UPDATE recipes SET doctor_id = ?, patient_id = ?, description = ?, " +
                "creation_date = ?, expiration_date = ?, priority = ? WHERE recipe_id = ?";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,recipe.getDoctor().getId());
            st.setLong(2,recipe.getPatient().getId());
            st.setString(3,recipe.getDescription());
            st.setDate(4, new Date(recipe.getCreationDate().getTime()));
            st.setDate(5, new Date(recipe.getExpirationDate().getTime()));
            st.setString(6,recipe.getPriority());
            st.setLong(7,recipe.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
