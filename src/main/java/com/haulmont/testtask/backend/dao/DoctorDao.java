package com.haulmont.testtask.backend.dao;

import com.haulmont.testtask.backend.entity.Doctor;
import com.haulmont.testtask.service.DBService;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao implements DaoDoctor {

    @Override
    public List<Doctor> getAll(){
        List<Doctor> list = new ArrayList<Doctor>();
        String sql = "SELECT * FROM doctors;";
        try (Connection connection = DBService.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getLong("doctor_id"));
                doctor.setFirstName(rs.getString("firstname"));
                doctor.setLastName(rs.getString("lastname"));
                doctor.setPatronymic(rs.getString("patronymic"));
                doctor.setSpecialization(rs.getString("specialization"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    public Doctor getById(Long id){
        Doctor doctor = null;
        String sql = "SELECT * FROM doctors WHERE doctor_id = ?;";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                doctor = new Doctor();
                doctor.setId(rs.getLong("doctor_id"));
                doctor.setFirstName(rs.getString("firstname"));
                doctor.setLastName(rs.getString("lastname"));
                doctor.setPatronymic(rs.getString("patronymic"));
                doctor.setSpecialization(rs.getString("specialization"));
            }
        } catch (SQLException e) {
            doctor = null;
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public void delete(Doctor doctor){
        String sql = "DELETE FROM doctors WHERE doctor_id = ?;";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setLong(1,doctor.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Doctor doctor){
        String sql = "INSERT INTO doctors(firstname, lastname, patronymic, specialization) VALUES(?,?,?,?)";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, doctor.getFirstName());
            st.setString(2, doctor.getLastName());
            st.setString(3, doctor.getPatronymic());
            st.setString(4, doctor.getSpecialization());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void change(Doctor doctor){
        String sql = "UPDATE doctors set firstname = ?, lastname = ?," +
                "patronymic = ?, specialization = ? WHERE doctor_id=?";
        try (Connection connection = DBService.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, doctor.getFirstName());
            st.setString(2, doctor.getLastName());
            st.setString(3, doctor.getPatronymic());
            st.setString(4, doctor.getSpecialization());
            st.setLong(5, doctor.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    };
}
