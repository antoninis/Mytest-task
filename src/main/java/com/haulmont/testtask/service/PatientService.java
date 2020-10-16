package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.dao.DaoPatient;
import com.haulmont.testtask.backend.dao.PatientDao;
import com.haulmont.testtask.backend.entity.Patient;
import java.util.List;

public class PatientService {
    private static DaoPatient Dao = new PatientDao();

    public static List<Patient> getAll() {
        return Dao.getAll();
    }

    public void delete(Patient patient){
        Dao.delete(patient);
    }

    public static void save(Patient patient) { Dao.save(patient);}

    public static void change(Patient patient) { Dao.change(patient);}
}
