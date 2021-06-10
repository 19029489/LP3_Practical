package com.example.lp3practical;

public class Drink {
    private String id;
    private String name;
    private String category;

    public Drink(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
