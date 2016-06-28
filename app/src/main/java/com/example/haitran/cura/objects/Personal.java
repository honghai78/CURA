package com.example.haitran.cura.objects;

/**
 * Created by hai.tran on 6/28/2016.
 */
public class Personal {

    private String name, level;
    public Personal(String name, String level)
    {
        this.name=name;
        this.level=level;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
