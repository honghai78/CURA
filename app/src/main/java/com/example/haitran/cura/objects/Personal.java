package com.example.haitran.cura.objects;

/**
 * Created by hai.tran on 6/28/2016.
 */
public class Personal {

    private String name, level, phone;
    public Personal(String name, String level, String phone)
    {
        this.name=name;
        this.level=level;
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone;
    }
}
