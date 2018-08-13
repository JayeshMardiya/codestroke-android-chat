package com.simi.codestrokealert.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CasesResponse {

    @SerializedName("result")
    private List<Cases> results;

    private boolean success;
    private String error_type;

    public String getError_type() {
        return error_type;
    }

    public List<Cases> getResults() {
        return results;
    }

    public void setResults(List<Cases> results) {
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }
}
