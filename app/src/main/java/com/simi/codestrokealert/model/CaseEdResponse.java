package com.simi.codestrokealert.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CaseEdResponse {

    @SerializedName("result")
    private List<CaseEds> results;

    public List<CaseEds> getResults() {
        return results;
    }

    public void setResults(List<CaseEds> results) {
        this.results = results;
    }
}
