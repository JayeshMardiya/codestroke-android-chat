package com.codestroke.codestrokealert.model;

public class CaseManagements {
    private int case_id;
    private byte thrombolysis;
    private byte new_trauma_haemorrhage;
    private byte uncontrolled_htn;
    private byte history_ich;
    private byte known_intracranial;
    private byte active_bleed;
    private byte endocarditis;
    private byte bleeding_diathesis;
    private byte abnormal_blood_glucose;
    private byte rapidly_improving;
    private byte recent_trauma_surgery;
    private byte recent_active_bleed;
    private byte seizure_onset;
    private byte recent_arterial_puncture;
    private byte recent_lumbar_puncture;
    private byte post_acs_pericarditis;
    private byte pregnant;
    private String thrombolysis_time_given;
    private byte ecr;
    private byte surgical_rx;
    private byte conservative_rx;
    private String dob;
    private String last_well;
    private byte ich_found;
    private byte large_vessel_occlusion;
    private String signoff_first_name;
    private String signoff_last_name;
    private String signoff_role;


    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public byte isThrombolysis() {
        return thrombolysis;
    }

    public void setThrombolysis(byte thrombolysis) {
        this.thrombolysis = thrombolysis;
    }

    public byte getNew_trauma_haemorrhage() {
        return new_trauma_haemorrhage;
    }

    public void setNew_trauma_haemorrhage(byte new_trauma_haemorrhage) {
        this.new_trauma_haemorrhage = new_trauma_haemorrhage;
    }

    public byte getUncontrolled_htn() {
        return uncontrolled_htn;
    }

    public void setUncontrolled_htn(byte uncontrolled_htn) {
        this.uncontrolled_htn = uncontrolled_htn;
    }

    public byte getHistory_ich() {
        return history_ich;
    }

    public void setHistory_ich(byte history_ich) {
        this.history_ich = history_ich;
    }

    public byte getKnown_intracranial() {
        return known_intracranial;
    }

    public void setKnown_intracranial(byte known_intracranial) {
        this.known_intracranial = known_intracranial;
    }

    public byte isActive_bleed() {
        return active_bleed;
    }

    public void setActive_bleed(byte active_bleed) {
        this.active_bleed = active_bleed;
    }

    public byte getEndocarditis() {
        return endocarditis;
    }

    public void setEndocarditis(byte endocarditis) {
        this.endocarditis = endocarditis;
    }

    public byte getBleeding_diathesis() {
        return bleeding_diathesis;
    }

    public void setBleeding_diathesis(byte bleeding_diathesis) {
        this.bleeding_diathesis = bleeding_diathesis;
    }

    public byte getAbnormal_blood_glucose() {
        return abnormal_blood_glucose;
    }

    public void setAbnormal_blood_glucose(byte abnormal_blood_glucose) {
        this.abnormal_blood_glucose = abnormal_blood_glucose;
    }

    public byte getRapidly_improving() {
        return rapidly_improving;
    }

    public void setRapidly_improving(byte rapidly_improving) {
        this.rapidly_improving = rapidly_improving;
    }

    public byte getRecent_trauma_surgery() {
        return recent_trauma_surgery;
    }

    public void setRecent_trauma_surgery(byte recent_trauma_surgery) {
        this.recent_trauma_surgery = recent_trauma_surgery;
    }

    public byte getRecent_active_bleed() {
        return recent_active_bleed;
    }

    public void setRecent_active_bleed(byte recent_active_bleed) {
        this.recent_active_bleed = recent_active_bleed;
    }

    public byte getSeizure_onset() {
        return seizure_onset;
    }

    public void setSeizure_onset(byte seizure_onset) {
        this.seizure_onset = seizure_onset;
    }

    public byte getRecent_arterial_puncture() {
        return recent_arterial_puncture;
    }

    public void setRecent_arterial_puncture(byte recent_arterial_puncture) {
        this.recent_arterial_puncture = recent_arterial_puncture;
    }

    public byte getRecent_lumbar_puncture() {
        return recent_lumbar_puncture;
    }

    public void setRecent_lumbar_puncture(byte recent_lumbar_puncture) {
        this.recent_lumbar_puncture = recent_lumbar_puncture;
    }

    public byte getPost_acs_pericarditis() {
        return post_acs_pericarditis;
    }

    public void setPost_acs_pericarditis(byte post_acs_pericarditis) {
        this.post_acs_pericarditis = post_acs_pericarditis;
    }

    public byte getPregnant() {
        return pregnant;
    }

    public void setPregnant(byte pregnant) {
        this.pregnant = pregnant;
    }

    public String getThrombolysis_time_given() {
        return thrombolysis_time_given;
    }

    public void setThrombolysis_time_given(String thrombolysis_time_given) {
        this.thrombolysis_time_given = thrombolysis_time_given;
    }

    public byte getEcr() {
        return ecr;
    }

    public void setEcr(byte ecr) {
        this.ecr = ecr;
    }

    public byte getSurgical_rx() {
        return surgical_rx;
    }

    public void setSurgical_rx(byte surgical_rx) {
        this.surgical_rx = surgical_rx;
    }

    public byte getConservative_rx() {
        return conservative_rx;
    }

    public void setConservative_rx(byte conservative_rx) {
        this.conservative_rx = conservative_rx;
    }

    public String getDob(){
        return dob;
    }

    public String getLast_well(){
        return last_well;
    }

    public byte getIch_found(){
        return ich_found;
    }

    public byte getLarge_vessel_occlusion(){
        return large_vessel_occlusion;
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

