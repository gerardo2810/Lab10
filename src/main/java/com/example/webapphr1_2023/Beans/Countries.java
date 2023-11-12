package com.example.webapphr1_2023.Beans;

import javax.swing.plaf.synth.Region;

public class Countries {
    private String country_id;
    private  String country_name;
    private Regions region_id;

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public Regions getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Regions region_id) {
        this.region_id = region_id;
    }
}
