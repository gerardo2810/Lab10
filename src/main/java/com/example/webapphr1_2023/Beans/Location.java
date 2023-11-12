package com.example.webapphr1_2023.Beans;

public class Location {
    private int locationId;
    private String street_address;
    private String postal_code;
    private String city;
    private String state_province;
    //private Countries country_id;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
