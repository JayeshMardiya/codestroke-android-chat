package com.simi.codestrokealert.model;

import java.io.Serializable;

public class CaseAssessments implements Serializable{
    private Integer case_id;
    private String facial_droop;
    private String arm_drift;
    private String weak_grip;
    private String speech_difficulty;
    private Integer bp_systolic;
    private Integer bp_diastolic;
    private Integer heart_rate;
    private String heart_rhythm;
    private Integer rr;
    private Integer o2sats;
    private Integer temp;
    private Integer gcs;
    private Integer blood_glucose;
    private byte facial_palsy_race;
    private byte arm_motor_impair;
    private byte leg_motor_impair;
    private byte head_gaze_deviate;
    private String hemiparesis;
    private String cannula;
    private byte conscious_level;
    private byte month_age;
    private byte blink_squeeze;
    private byte horizontal_gaze;
    private byte visual_fields;
    private byte facial_palsy_nihss;
    private byte left_arm_drift;
    private byte right_arm_drift;
    private byte left_leg_drift;
    private byte right_leg_drift;
    private byte limb_ataxia;
    private byte sensation;
    private byte aphasia;
    private byte dysarthria;
    private byte neglect;
    private byte rankin_conscious;
    private byte likely_lvo;
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

    public Integer getCase_id() {
        return case_id;
    }

    public void setCase_id(Integer case_id) {
        this.case_id = case_id;
    }

    public String getFacial_droop() {
        return facial_droop;
    }

    public void setFacial_droop(String facial_droop) {
        this.facial_droop = facial_droop;
    }

    public String getArm_drift() {
        return arm_drift;
    }

    public void setArm_drift(String arm_drift) {
        this.arm_drift = arm_drift;
    }

    public String getWeak_grip() {
        return weak_grip;
    }

    public void setWeak_grip(String weak_grip) {
        this.weak_grip = weak_grip;
    }

    public String getSpeech_difficulty() {
        return speech_difficulty;
    }

    public void setSpeech_difficulty(String speech_difficulty) {
        this.speech_difficulty = speech_difficulty;
    }

    public Integer getBp_systolic() {
        return bp_systolic;
    }

    public void setBp_systolic(Integer bp_systolic) {
        this.bp_systolic = bp_systolic;
    }

    public Integer getBp_diastolic() {
        return bp_diastolic;
    }

    public void setBp_diastolic(Integer bp_diastolic) {
        this.bp_diastolic = bp_diastolic;
    }

    public Integer getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(Integer heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getHeart_rhythm() {
        return heart_rhythm;
    }

    public void setHeart_rhythm(String heart_rhythm) {
        this.heart_rhythm = heart_rhythm;
    }

    public Integer getRr() {
        return rr;
    }

    public void setRr(Integer rr) {
        this.rr = rr;
    }

    public Integer getO2sats() {
        return o2sats;
    }

    public void setO2sats(Integer o2sats) {
        this.o2sats = o2sats;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getGcs() {
        return gcs;
    }

    public void setGcs(Integer gcs) {
        this.gcs = gcs;
    }

    public Integer getBlood_glucose() {
        return blood_glucose;
    }

    public void setBlood_glucose(Integer blood_glucose) {
        this.blood_glucose = blood_glucose;
    }

    public byte getFacial_palsy_race() {
        return facial_palsy_race;
    }

    public void setFacial_palsy_race(byte facial_palsy_race) {
        this.facial_palsy_race = facial_palsy_race;
    }

    public byte getArm_motor_impair() {
        return arm_motor_impair;
    }

    public void setArm_motor_impair(byte arm_motor_impair) {
        this.arm_motor_impair = arm_motor_impair;
    }

    public byte getLeg_motor_impair() {
        return leg_motor_impair;
    }

    public void setLeg_motor_impair(byte leg_motor_impair) {
        this.leg_motor_impair = leg_motor_impair;
    }

    public byte getHead_gaze_deviate() {
        return head_gaze_deviate;
    }

    public void setHead_gaze_deviate(byte head_gaze_deviate) {
        this.head_gaze_deviate = head_gaze_deviate;
    }

    public String getHemiparesis() {
        return hemiparesis;
    }

    public void setHemiparesis(String hemiparesis) {
        this.hemiparesis = hemiparesis;
    }

    public String getCannula() {
        return cannula;
    }

    public void setCannula(String cannula) {
        this.cannula = cannula;
    }

    public byte getConscious_level() {
        return conscious_level;
    }

    public void setConscious_level(byte conscious_level) {
        this.conscious_level = conscious_level;
    }

    public byte getMonth_age() {
        return month_age;
    }

    public void setMonth_age(byte month_age) {
        this.month_age = month_age;
    }

    public byte getBlink_squeeze() {
        return blink_squeeze;
    }

    public void setBlink_squeeze(byte blink_squeeze) {
        this.blink_squeeze = blink_squeeze;
    }

    public byte getHorizontal_gaze() {
        return horizontal_gaze;
    }

    public void setHorizontal_gaze(byte horizontal_gaze) {
        this.horizontal_gaze = horizontal_gaze;
    }

    public byte getVisual_fields() {
        return visual_fields;
    }

    public void setVisual_fields(byte visual_fields) {
        this.visual_fields = visual_fields;
    }

    public byte getFacial_palsy_nihss() {
        return facial_palsy_nihss;
    }

    public void setFacial_palsy_nihss(byte facial_palsy_nihss) {
        this.facial_palsy_nihss = facial_palsy_nihss;
    }

    public byte getLeft_arm_drift() {
        return left_arm_drift;
    }

    public void setLeft_arm_drift(byte left_arm_drift) {
        this.left_arm_drift = left_arm_drift;
    }

    public byte getRight_arm_drift() {
        return right_arm_drift;
    }

    public void setRight_arm_drift(byte right_arm_drift) {
        this.right_arm_drift = right_arm_drift;
    }

    public byte getLeft_leg_drift() {
        return left_leg_drift;
    }

    public void setLeft_leg_drift(byte left_leg_drift) {
        this.left_leg_drift = left_leg_drift;
    }

    public byte getRight_leg_drift() {
        return right_leg_drift;
    }

    public void setRight_leg_drift(byte right_leg_drift) {
        this.right_leg_drift = right_leg_drift;
    }

    public byte getLimb_ataxia() {
        return limb_ataxia;
    }

    public void setLimb_ataxia(byte limb_ataxia) {
        this.limb_ataxia = limb_ataxia;
    }

    public byte getSensation() {
        return sensation;
    }

    public void setSensation(byte sensation) {
        this.sensation = sensation;
    }

    public byte getAphasia() {
        return aphasia;
    }

    public void setAphasia(byte aphasia) {
        this.aphasia = aphasia;
    }

    public byte getDysarthria() {
        return dysarthria;
    }

    public void setDysarthria(byte dysarthria) {
        this.dysarthria = dysarthria;
    }

    public byte getNeglect() {
        return neglect;
    }

    public void setNeglect(byte neglect) {
        this.neglect = neglect;
    }

    public byte getRankin_conscious() {
        return rankin_conscious;
    }

    public void setRankin_conscious(byte rankin_conscious) {
        this.rankin_conscious = rankin_conscious;
    }

    public byte getLikely_lvo() {
        return likely_lvo;
    }

    public void setLikely_lvo(byte likely_lvo) {
        this.likely_lvo = likely_lvo;
    }




}
