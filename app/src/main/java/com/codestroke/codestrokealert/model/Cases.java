package com.codestroke.codestrokealert.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cases implements Serializable{

    private String address;
    private int case_id;
    private String dob;
    private String first_name;
    private String gender;
    private String last_name;
    private String last_well;
    private String medicare_no;
    private String nok;
    private String nok_phone;
    private String status;
    private int hospital_id;
    private String eta;
    private String active_timestamp;
    private String incoming_timestamp;
    private String completed_timestamp;
    private BigDecimal initial_location_lat;
    private BigDecimal initial_location_long;
    private String signoff_first_name;
    private String signoff_last_name;
    private String signoff_role;

    public void setSignoff_first_name(String signoff_first_name) {
        this.signoff_first_name = signoff_first_name;
    }

    public void setSignoff_last_name(String signoff_last_name) {
        this.signoff_last_name = signoff_last_name;
    }

    public void setSignoff_role(String signoff_role) {
        this.signoff_role = signoff_role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status_time;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_well() {
        return last_well;
    }

    public void setLast_well(String last_well) {
        this.last_well = last_well;
    }

    public String getMedicare_no() {
        return medicare_no;
    }

    public void setMedicare_no(String medicare_no) {
        this.medicare_no = medicare_no;
    }

    public String getNok() {
        return nok;
    }

    public void setNok(String nok) {
        this.nok = nok;
    }

    public String getNok_phone() {
        return nok_phone;
    }

    public void setNok_phone(String nok_phone) {
        this.nok_phone = nok_phone;
    }

    public String getStatus_time() {
        return status_time;
    }

    public void setStatus_time(String status_time) {
        this.status_time = status_time;
    }

    public void setHopspital_id(int hospital_id){
        this.hospital_id = hospital_id;
    }

    public String getActive_timestamp() {
        return active_timestamp;
    }

    public void setActive_timestamp(String active_timestamp) {
        this.active_timestamp = active_timestamp;
    }

    public String getIncoming_timestamp() {
        return incoming_timestamp;
    }

    public void setIncoming_timestamp(String incoming_timestamp) {
        this.incoming_timestamp = incoming_timestamp;
    }

    public String getCompleted_timestamp() {
        return completed_timestamp;
    }

    public void setCompleted_timestamp(String completed_timestamp) {
        this.completed_timestamp = completed_timestamp;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public BigDecimal getInitial_location_lat() {
        return initial_location_lat;
    }

    public void setInitial_location_lat(BigDecimal initial_location_lat) {
        this.initial_location_lat = initial_location_lat;
    }

    public BigDecimal getInitial_location_long() {
        return initial_location_long;
    }

    public void setInitial_location_long(BigDecimal initial_location_long) {
        this.initial_location_long = initial_location_long;
    }



}
