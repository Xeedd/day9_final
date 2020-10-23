package com.example.day9_final;

public class Food {

    int id;
    String food_name;

    public Food(){

    }

    public Food(String name) {
        this.id = id;
        this.food_name = name;
    }

    public Food(int id, String name) {
        this.id = id;
        this.food_name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return food_name;
    }

    public void setName(String name) {
        this.food_name = name;
    }
}
