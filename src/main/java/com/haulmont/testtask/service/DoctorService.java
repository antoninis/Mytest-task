package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.dao.DaoDoctor;
import com.haulmont.testtask.backend.dao.DoctorDao;
import com.haulmont.testtask.backend.entity.Doctor;
import java.util.List;

public class DoctorService  {
    private static DaoDoctor Dao = new DoctorDao();

    public static void delete(Doctor doctor){
        Dao.delete(doctor);
    }

    public static List<Doctor> getAll() {
        return Dao.getAll();
    }

    public static void save(Doctor doctor) { Dao.save(doctor);}

    public static void change(Doctor doctor) { Dao.change(doctor);}

}
