package com.company;

// Imports necessary Java classes
import java.util.Arrays;
import java.util.*;

// This class represents the card deck
public class Deck {
    // Creates the component of card decks
    private static int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11};
    private static String[] symbols = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private static String[] suits = {"heart", "clubs", "spades", "diamonds"};
    private Card[] cards;
    private int currentIndex;

    // Creates deck
    public Deck() {
        // Initializes member states
        this.currentIndex = 0;
        this.cards = new Card[52];

        // create 52 cards in different value, symbol, and suit
        for (int i = 0; i < symbols.length; i++){
            Card c = new Card(values[i], symbols[i], suits[0]);
            this.cards[i] = c;
            Card c1 = new Card(values[i], symbols[i], suits[1]);
            this.cards[i + 13] = c1;
            Card c2 = new Card(values[i], symbols[i], suits[2]);
            this.cards[i + 26] = c2;
            Card c3 = new Card(values[i], symbols[i], suits[3]);
            this.cards[i + 39] = c3;
        }
    }

    // Increases index of cards member function
    public Card next(){
        return cards[currentIndex++];
    }

    // Shuffles cards by randomly swapping cards
    public void shuffle() {
        for (int i = 0; i < cards.length;){
            int index1 = (int)(Math.random()*52);
            int index2 = (int)(Math.random()*52);

            if(index1 == index2) continue;
            i++;
            Card temp = cards[index1];
            cards[index1] = cards[index2];
            cards[index2] = temp;
        }
    }

    /*
  Original attempt

    public void shuffle2() {
        Card shuffledCards [] = new Card[this.cards.length];

        for (int i = 0; i < cards.length; ){
            int index = (int)(Math.random()*52);

            if (shuffledCards[index] == null){
                shuffledCards[index] = this.cards[i];
                i++;
            }
        }
        this.cards = shuffledCards;
    }

    Used for testing
    public static void main(String[] args){
        Deck d = new Deck();
        d.shuffle();

        System.out.println(d);
    }
    */

    @Override
    // Default print deck toString object
    public String toString() {
        StringBuffer sb;
        sb = new StringBuffer();

        for (int i = 0; i < cards.length; i++) {
            sb.append("card[");

            sb.append(i);
            sb.append("]:");
            sb.append(cards[i].toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
