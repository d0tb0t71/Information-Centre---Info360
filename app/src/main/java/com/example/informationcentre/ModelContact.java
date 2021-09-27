package com.example.informationcentre;

public class ModelContact {

    String name,mobile;

    public ModelContact() {
    }

    public ModelContact(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }


    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }
}
