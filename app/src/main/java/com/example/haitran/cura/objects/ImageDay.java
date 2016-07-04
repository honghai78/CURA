package com.example.haitran.cura.objects;

/**
 * Created by kha.phan on 7/4/2016.
 */
public class ImageDay {
    private String day;
    private String[] urlImage;

    public ImageDay(String day, String[] urlImage) {
        this.day = day;
        this.urlImage = urlImage;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String[] getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String[] urlImage) {
        this.urlImage = urlImage;
    }
}
