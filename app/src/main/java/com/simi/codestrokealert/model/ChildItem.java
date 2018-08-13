package com.simi.codestrokealert.model;

public class ChildItem {

    private String childKey;
    private String childValue;

    public ChildItem(String childKey, String childValue) {
        this.childKey = childKey;
        this.childValue = childValue;
    }

    public String getChildKey() {

        return childKey;
    }

    public void setChildKey(String childKey) {
        this.childKey = childKey;
    }

    public String getChildValue() {
        return childValue;
    }

    public void setChildValue(String childValue) {
        this.childValue = childValue;
    }
}
