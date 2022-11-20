package com.example.dogapp2.Model;

public class DogModel {

    private String dogName;
    private int image;
    String description;
    private int imageResourse;
    private String title;
    private String key_id;
    private String favStatus;

    public DogModel(String dogName, int image, String description, String key_id) {
        this.dogName = dogName;
        this.image = image;
        this.description = description;
        this.imageResourse = imageResourse;
        this.title = title;
        this.key_id = key_id;
        favStatus="0";
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String getKey_id() {
        return key_id;
    }

    public String getFavStatus() {
        return favStatus = "0";
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;

    }
    public String getDogName() {
        return dogName;
    }

    public int getImage() {
        return image;
    }

}
