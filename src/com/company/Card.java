package com.company;

// This class represents the cards
public class Card {

    // Member variable-state of card
    private int value;
    private String symbol;
    private String suit;

    // Default constructor
    public Card(){

    }

    // Constructor
    public Card(int value, String symbol, String suit) {
        this.value = value;
        this.symbol = symbol;
        this.suit = suit;
    }

    // Getters and setters
    public int getValue() {
        return value;
    }

    // setter of value
    public void setValue(int value) {
        this.value = value;
    }

    // getter of symbol
    public String getSymbol() {
        return symbol;
    }

    // setter of symbol
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // getter of suit
    public String getSuit() {
        return suit;
    }

    // setter of suit
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // Determines if card is an ace
    public Boolean isAce() {
        return this.symbol.equalsIgnoreCase("A");
    }

    // Gets second value of ace card
    public int getSecondValue() {
        if (this.isAce()){
            return 1;
        }
        else{
            return this.getValue();
        }
    }

    @Override
    // Default print deck toString object
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", symbol='" + symbol + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}
