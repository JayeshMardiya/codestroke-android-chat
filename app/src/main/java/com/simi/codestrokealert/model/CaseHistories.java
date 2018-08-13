package com.simi.codestrokealert.model;


public class CaseHistories {

    private int case_id;
    private String pmhx;
    private String meds;
    private String anticoags;
    private String anticoags_last_dose;
    private String hopc;
    private float weight;
    private String last_meal;
    private String signoff_first_name;
    private String signoff_last_name;
    private String signoff_role;


    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getPmhx() {
        return pmhx;
    }

    public void setPmhx(String pmhx) {
        this.pmhx = pmhx;
    }

    public String getMeds() {
        return meds;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }

    public String getAnticoags() {
        return anticoags;
    }

    public void setAnticoags(String anticoags) {
        this.anticoags = anticoags;
    }

    public String getAnticoags_last_dose() {
        return anticoags_last_dose;
    }

    public void setAnticoags_last_dose(String anticoags_last_dose) {
        this.anticoags_last_dose = anticoags_last_dose;
    }

    public String getHopc() {
        return hopc;
    }

    public void setHopc(String hopc) {
        this.hopc = hopc;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getLast_meal() {
        return last_meal;
    }

    public void setLast_meal(String last_meal) {
        this.last_meal = last_meal;
    }

    public void setSignoff_first_name(String signoff_first_name) {
        this.signoff_first_name = signoff_first_name;
    }

    public void setSignoff_last_name(String signoff_last_name) {
        this.signoff_last_name = signoff_last_name;
    }

    public void setSignoff_role(String signoff_role) {
        this.signoff_role = signoff_role;
    }
}
