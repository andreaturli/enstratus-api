package com.enstratus.api.model;

public class CurrentUsage {

    private String value;
    private String currency;
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
