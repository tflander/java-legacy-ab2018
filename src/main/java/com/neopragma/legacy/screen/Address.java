package com.neopragma.legacy.screen;

public class Address {

    private String zipCode;
    private String city;
    private String state;

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}
