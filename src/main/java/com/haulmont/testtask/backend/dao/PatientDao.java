package com.haulmont.testtask.backend.dao;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.backend.entity.Patient;
import com.haulmont.testtask.service.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements DaoPatient {

    @Override
    public List<Patient> getAll(){
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients;";
        try (Connection connection = DBService.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getLong("patient_id"));
                patient.setFirstName(rs.getString("firstname"));
                patient.setLastName(rs.getString("lastname"));
                patient.setPatronymic(rs.getString("patronymic"));
                patient.setPhone(rs.getString("phone"));
                list.add(patient);
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    public Patient getById(Long id){
        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE patient_id = ?;";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                patient = new Patient();
                patient.setId(rs.getLong("patient_id"));
                patient.setFirstName(rs.getString("firstname"));
                patient.setLastName(rs.getString("lastname"));
                patient.setPatronymic(rs.getString("patronymic"));
                patient.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            patient = null;
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public void delete(Patient patient){
        String sql = "DELETE FROM patients WHERE patient_id = ?;";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,patient.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Patient patient) {
        String sql = "INSERT INTO patients(firstname, lastname, patronymic, phone) VALUES(?,?,?,?)";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, patient.getFirstName());
            st.setString(2, patient.getLastName());
            st.setString(3, patient.getPatronymic());
            st.setString(4, patient.getPhone());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void change(Patient patient) {
        String sql = "UPDATE patients set firstname = ?, lastname = ?," +
                "patronymic = ?, phone = ? WHERE patient_id=?";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, patient.getFirstName());
            st.setString(2, patient.getLastName());
            st.setString(3, patient.getPatronymic());
            st.setString(4, patient.getPhone());
            st.setLong(5, patient.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
