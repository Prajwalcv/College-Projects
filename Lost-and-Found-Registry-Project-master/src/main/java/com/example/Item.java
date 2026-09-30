package com.example;

import java.time.LocalDate;

public class Item{
    private int id;
    private String name;
    private String category;
    private String location;
    private LocalDate dateFound;;

    public Item(int id,String name,String category,String location,LocalDate dateFound){
    this.id=id;
    this.name=name;
    this.category=category;
    this.location=location;
    this.dateFound=dateFound;
    }
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getLocation() { return location; }
    public LocalDate getDateFound() { return dateFound; }
}