package com.example.haitran.cura.data;

import com.example.haitran.cura.models.Patient;

import java.util.Comparator;

/**
 * Created by hanh.tran on 7/1/2016.
 */
public class CustomComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient lhs, Patient rhs) {
        return lhs.getTimeArrival().compareTo(rhs.getTimeArrival());
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}
