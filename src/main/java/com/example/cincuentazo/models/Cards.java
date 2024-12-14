package com.example.cincuentazo.models;

/**
 * The Cards class represents a card in the game with a type, values, and an identifier.
 */
public class Cards {
    /**
     * The type of the card.
     */
    private String type;
    /**
     * The first value of the card.
     */
    private int firstValue;
    /**
     * The second value of the card, default is 0.
     */
    private int secondValue = 0;
    /**
     * The unique identifier of the card.
     */
    private String id;
    /**
     * Indicates whether the card is taken or not.
     */
    private boolean isTaken = false;
    /**
     * Constructs a new card with the specified type, values, and identifier.
     *
     * @param type the type of the card
     * @param firstValue the first value of the card
     * @param id the unique identifier of the card
     * @param secondValue the second value of the card
     */
    public Cards(String type, int firstValue, String id, int secondValue) {
        this.type = type;
        this.firstValue = firstValue;
        this.id = id;
        this.secondValue = secondValue;
    }

    public String getImage() {
        return "/com/example/cincuentazo/images/cards/" + type + "/" + id + ".png";
    }
    /**
     * Gets the unique identifier of the card.
     *
     * @return the unique identifier of the card
     */
    public String getId() {return this.id;}
    /**
     * Sets the unique identifier of the card.
     *
     * @param id the unique identifier of the card
     */
    public void setId(String id) {this.id = id;}
    /**
     * Gets the type of the card.
     *
     * @return the type of the card
     */
    public String getType() {return type;}
    /**
     * Sets the type of the card.
     *
     * @param type the type of the card
     */
    public void setType(String type) {this.type = type;}
    /**
     * Gets the first value of the card.
     *
     * @return the first value of the card
     */
    public int getFirstValue() {return firstValue;}
    /**
     * Sets the first value of the card.
     *
     * @param firstValue the first value of the card
     */
    public void setFirstValue(int firstValue) {this.firstValue = firstValue;}
    /**
     * Gets the second value of the card.
     *
     * @return the second value of the card
     */
    public int getSecondValue() {return secondValue;}
    /**
     * Sets the second value of the card.
     *
     * @param secondValue the second value of the card
     */
    public void setSecondValue(int secondValue) {this.secondValue = secondValue;}
    /**
     * Checks if the card is taken.
     *
     * @return true if the card is taken, false otherwise
     */
    public boolean isTaken() {return isTaken;}
    /**
     * Sets the card as taken or not.
     *
     * @param taken true if the card is taken, false otherwise
     */
    public void setTaken(boolean taken) {isTaken = taken;}
    /**
     * Prints the attributes of the card.
     */
    public void printAttributes() {
        System.out.println("Type: " + type);
        System.out.println("First Value: " + firstValue);
        System.out.println("Second Value: " + secondValue);
        System.out.println("ID: " + id);
        System.out.println("Is Taken: " + isTaken);
    }
}
