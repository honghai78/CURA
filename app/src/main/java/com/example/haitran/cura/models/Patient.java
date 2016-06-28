package com.example.haitran.cura.models;

/**
 * Created by hanh.tran on 6/28/2016.
 */
public class Patient {
    private String id;
    private String name;
    private int age;
    private String code;
    private int gender;
    private String nameDoctor;
    private int numOfExam;

    public Patient(String id, String name, int age, String code, int gender, String nameDoctor, int numOfExam) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.code = code;
        this.gender = gender;
        this.nameDoctor = nameDoctor;
        this.numOfExam = numOfExam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        gender = gender;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public int getNumOfExam() {
        return numOfExam;
    }

    public void setNumOfExam(int numOfExam) {
        this.numOfExam = numOfExam;
    }
}
