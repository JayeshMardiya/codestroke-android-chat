package com.simi.codestrokealert.model;


public class CaseEds {

    private int case_id;
    private String location;
    private byte registered;
    private byte triaged;
    private byte primary_survey;
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

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte getRegistered() {
        return registered;
    }

    public void setRegistered(byte registered) {
        this.registered = registered;
    }

    public byte getTriaged() {
        return triaged;
    }

    public void setTriaged(byte triaged) {
        this.triaged = triaged;
    }

    public byte getPrimary_survey() {
        return primary_survey;
    }

    public void setPrimary_survey(byte primary_survey) {
        this.primary_survey = primary_survey;
    }




}
