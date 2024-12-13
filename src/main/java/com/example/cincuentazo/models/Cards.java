package com.example.cincuentazo.models;

public class Cards {

    private String type;
    private int firstValue;
    private int secondValue = 0;
    private String id;
    private boolean isTaken = false;
    public Cards(String type, int firstValue, String id, int secondValue) {
        this.type = type;
        this.firstValue = firstValue;
        this.id = id;
        this.secondValue = secondValue;
    }
    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public int getFirstValue() {return firstValue;}
    public void setFirstValue(int firstValue) {this.firstValue = firstValue;}
    public int getSecondValue() {return secondValue;}
    public void setSecondValue(int secondValue) {this.secondValue = secondValue;}
    public boolean isTaken() {return isTaken;}
    public void setTaken(boolean taken) {isTaken = taken;}
    public void printAttributes() {
        System.out.println("Type: " + type);
        System.out.println("First Value: " + firstValue);
        System.out.println("Second Value: " + secondValue);
        System.out.println("ID: " + id);
        System.out.println("Is Taken: " + isTaken);
    }
}
