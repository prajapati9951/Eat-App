package com.example.example.Model;

public class Category {
    private String Name;
    private String image;

    public Category(String name, String image) {
        Name = name;
        this.image = image;
    }

    public Category() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
