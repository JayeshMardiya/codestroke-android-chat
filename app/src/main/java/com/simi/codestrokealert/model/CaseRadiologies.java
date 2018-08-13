package com.simi.codestrokealert.model;


public class CaseRadiologies {

    private int case_id;
    private byte ct1;
    private byte ct2;
    private byte ct3;
    private byte arrived_to_ct;
    private byte ct_complete;
    private byte ich_found;
    private byte do_cta_ctp;
    private byte cta_ctp_complete;
    private byte large_vessel_occlusion;
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

    public byte getCt1() {
        return ct1;
    }

    public void setCt1(byte ct1) {
        this.ct1 = ct1;
    }

    public byte getCt2() {
        return ct2;
    }

    public void setCt2(byte ct2) {
        this.ct2 = ct2;
    }

    public byte getCt3() {
        return ct3;
    }

    public void setCt3(byte ct3) {
        this.ct3 = ct3;
    }

    public byte getArrived_to_ct() {
        return arrived_to_ct;
    }

    public void setArrived_to_ct(byte arrived_to_ct) {
        this.arrived_to_ct = arrived_to_ct;
    }

    public byte getCt_complete() {
        return ct_complete;
    }

    public void setCt_complete(byte ct_complete) {
        this.ct_complete = ct_complete;
    }

    public byte getIch_found() {
        return ich_found;
    }

    public void setIch_found(byte ich_found) {
        this.ich_found = ich_found;
    }

    public byte getDo_cta_ctp() {
        return do_cta_ctp;
    }

    public void setDo_cta_ctp(byte do_cta_ctp) {
        this.do_cta_ctp = do_cta_ctp;
    }

    public byte getCta_ctp_complete() {
        return cta_ctp_complete;
    }

    public void setCta_ctp_complete(byte cta_ctp_complete) {
        this.cta_ctp_complete = cta_ctp_complete;
    }

    public byte getLarge_vessel_occlusion() {
        return large_vessel_occlusion;
    }

    public void setLarge_vessel_occlusion(byte large_vessel_occlusion) {
        this.large_vessel_occlusion = large_vessel_occlusion;
    }


}
