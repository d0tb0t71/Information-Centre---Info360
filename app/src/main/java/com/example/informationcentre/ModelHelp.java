package com.example.informationcentre;

public class ModelHelp {
    String name,mobile,mssg;

    public ModelHelp() {
    }

    public ModelHelp(String name, String mobile, String mssg) {
        this.name = name;
        this.mobile = mobile;
        this.mssg = mssg;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMssg() {
        return mssg;
    }
}
