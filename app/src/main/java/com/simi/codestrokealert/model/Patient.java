package com.simi.codestrokealert.model;


import java.io.Serializable;

public class Patient implements Serializable {

    private String name;
    private String gender;
    private int age;
    private String eta;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient(String name, String gender, int age, String eta, int id) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.id = id;
        this.eta = eta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }
}
