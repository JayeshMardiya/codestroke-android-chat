package com.simi.codestrokealert.model;


public class Hospital {

    private String hospital_name;
    private String hospital_city;
    private String hospital_state;
    private String hospital_coords;
    private String hospital_url;
    private int hospital_id;
    private int highest_assigned_id;

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_city() {
        return hospital_city;
    }

    public void setHospital_city(String hospital_city) {
        this.hospital_city = hospital_city;
    }

    public String getHospital_state() {
        return hospital_state;
    }

    public void setHospital_state(String hospital_state) {
        this.hospital_state = hospital_state;
    }

    public String getHospital_coords() {
        return hospital_coords;
    }

    public void setHospital_coords(String hospital_coords) {
        this.hospital_coords = hospital_coords;
    }

    public String getHospital_url() {
        return hospital_url;
    }

    public void setHospital_url(String hospital_url) {
        this.hospital_url = hospital_url;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getHighest_assigned_id() {
        return highest_assigned_id;
    }
}
