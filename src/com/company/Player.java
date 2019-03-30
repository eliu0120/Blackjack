package com.company;

// Class represents player and dealer
public class Player {
    // Member variables - state of Player/dealer
    private Card[] cards;
    private boolean isDealer;

    // Constructor with a parameter to set if it's a regular player or dealer player
    public Player(boolean isDealer) {
        this.cards = new Card[11];

        for (int i = 0; i < cards.length; i++){
            this.cards[i] = null;
        }

        this.isDealer = isDealer;
    }

    // add a card to the player
    public void addCard(Card c){
        for (int i = 0; i < cards.length; i++){
            if (this.cards[i] == null){
                this.cards[i] = c;
                break;
            }
        }
    }

    // Determines if the player is a dealer
    public boolean isDealer() {
        return this.isDealer;
    }

    // Show hands, but hide first card if it's a dealer player
    public void showHands(){
        this.showHands(false);
    }

    // Shows the hands based on a parameter - show all or show with first card hidden if it's a dealer
    public void showHands(boolean showAllByForce){
        StringBuffer sb = new StringBuffer();

        sb.append((this.isDealer() ? "Dealer:" : "Player:"));
        for (int i = 0; i < cards.length; i++){
            if (!showAllByForce && i == 0 && this.isDealer() ){
                sb.append("First card Hidden");
            }
            else if (this.cards[i] != null){
                if(i > 0) {
                    sb.append(",");
                }
                sb.append(this.cards[i].getSuit()).append(":").append(this.cards[i].getSymbol());
            }
            else {
                break;
            }
        }
        sb.append(", total: ");
        boolean firstValue = false;
        int totalValue;
        if(showAllByForce) {
            totalValue = this.getTotalValue();
        } else {
            totalValue = this.getTotalValueExceptForHidden();
        }
        if (totalValue < 21) {
            firstValue = true;
            sb.append(totalValue);
        }
        boolean secondValue = false;
        if (totalValue != 21 && this.hasSecondValue() && !this.isDealer()) {
            if (firstValue) {
                sb.append("/");
            }
            sb.append(this.getSecondTotalValue());
            secondValue = true;
        }
        if (!firstValue && !secondValue){
            sb.append(totalValue);
        }
        System.out.println(sb);
    }

    // Determines the total value except for hidden cards in dealer
    public int getTotalValueExceptForHidden() {
        int sum = 0;

        for (int i = 0; i < cards.length; i++){
            if (this.isDealer() && i == 0){
                continue;
            }
            if (this.cards[i] != null){
                sum += this.cards[i].getValue();
            }
        }
        return sum;
    }

    // Determines true sum of all kept cards
    public int getTotalValue() {
        int sum = 0;

        for (int i = 0; i < cards.length; i++) {
            if (this.cards[i] != null) {
                sum += this.cards[i].getValue();
            } else {
                break;
            }
        }

        return sum;
    }

    // Determines if a card is an ace and if there's ace, then there's second value
    public boolean hasSecondValue() {
        for (int i = 0; i < cards.length; i++) {
            if (this.cards[i] == null){
                break;
            }
            else if (this.cards[i].isAce()) {
                return true;
            }
        }
        return false;
    }

    // Gets the total value except for hidden card if it's a dealer player
    public int getSecondTotalValueExceptForHidden() {
        int sum = 0;
        for (int i = 0; i < cards.length; i++) {
            if (this.isDealer() && i == 0){
                continue;
            }
            if (this.cards[i] != null) {
                sum += this.cards[i].getSecondValue();
            }
            else{
                break;
            }
        }

        return sum;
    }

    // Gets second total value for a player or dealer
    public int getSecondTotalValue() {
        int sum = 0;

        for (int i = 0; i < cards.length; i++) {
            if (this.cards[i] != null) {
                sum += this.cards[i].getSecondValue();
            }
            else{
                break;
            }
        }

        return sum;
    }

    // Displays the sums of the cards
    public void showTotal() {
        int s = this.getTotalValue();

        StringBuffer sb;
        sb = new StringBuffer();

        sb.append(s);
        if (this.hasSecondValue()){
            sb.append(" or " + this.getSecondTotalValue());
        }

        System.out.println(sb);
    }

}
