package com.simi.codestrokealert.model;

import com.simi.codestrokealert.model.CaseHistories;

import java.util.List;

public class CaseHistoriesResponse {

    private List<CaseHistories> result;
    public List<CaseHistories> getResult() {
        return result;
    }
    public void setResult(List<CaseHistories> result) {
        this.result = result;
    }

}
