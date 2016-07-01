package com.example.haitran.cura.data;

import com.example.haitran.cura.models.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hanh.tran on 6/30/2016.
 */
public class MyData {

    private List<Patient> registeredPatientList = new ArrayList<Patient>() {{
        add(new Patient("10000" + 0, "abc 1", 77, "000000000" + 0, 0, "Tom", 1));
        add(new Patient("10000" + 1, "abc 2", 89, "000000000" + 1, 1, "Jerry", 0));
        add(new Patient("10000" + 2, "abc 3", 77, "000000000" + 2, 0, "Jenifer", 2));
        add(new Patient("10000" + 3, "abc 4", 27, "000000000" + 3, 0, "Peter", 1));
        add(new Patient("10000" + 4, "abc 5", 19, "000000000" + 4, 1, "Jerry", 0));
        add(new Patient("10000" + 5, "abc 6", 47, "000000000" + 5, 0, "Tom", 2));
    }};

    private List<Patient> patientListInQueue = new ArrayList<>();

    public MyData() {
    }

    public void addPatientInQueue(Patient patient) {
        patientListInQueue.add(patient);
    }

    public List<Patient> getPatientListInQueue() {
        return patientListInQueue;
    }

    public List<Patient> getRegisteredPatientList() {
        return registeredPatientList;
    }

    public void removeRegisteredPatient(int position) {
        registeredPatientList.remove(position);
    }

    public List<Patient> sortPatientByTimeInQueue(List<Patient> patients) {
        Collections.sort(patients, new CustomComparator());
        return patients;
    }
}
