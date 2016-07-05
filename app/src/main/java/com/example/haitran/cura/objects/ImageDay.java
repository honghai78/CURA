package com.example.haitran.cura.objects;

import java.util.ArrayList;

/**
 * Created by kha.phan on 7/4/2016.
 */
public class ImageDay {
    private String day;
    private ArrayList<String> urlImage;

    public ImageDay(String day, ArrayList<String> urlImage) {
        this.day = day;
        this.urlImage = urlImage;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<String> getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(ArrayList<String> urlImage) {
        this.urlImage = urlImage;
    }
}
