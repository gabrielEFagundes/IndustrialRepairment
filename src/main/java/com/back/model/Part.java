package com.back.model;

public class Part {

    private int id;
    private String name;
    private double storage;

    public Part(String name, double storage) {
        this.name = name;
        this.storage = storage;
    }

    public Part(int id, String name, double storage) {
        this.id = id;
        this.name = name;
        this.storage = storage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }
}
