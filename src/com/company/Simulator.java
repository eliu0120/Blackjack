package com.company;

import java.util.Scanner;

// This class represents the simulator
public class Simulator {
    public static void main(String[] args) {
        // 0:Loop forever
        Scanner input = new Scanner(System.in);
        System.out.print("Play Blackjack now? Y/N: ");
        String answer = input.nextLine();

        for (; ; ) {
            if (!answer.equalsIgnoreCase("Y")) {
                System.out.println("Goodbye");
                break;
            }
            // 1:Shuffle a new deck to play black jack
            Deck d = new Deck();
            d.shuffle();

            // 2: Create A player, and a Dealer, then draw 2 cards for each - player's and dealer's initial deck
            Player player = new Player(false);
            Player dealer = new Player(true);

            // give player two cards first
            Card c1 = d.next();
            player.addCard(c1);
            Card c2 = d.next();
            player.addCard(c2);

            // give dealer two cards
            Card c3 = d.next();
            dealer.addCard(c3);
            Card c4 = d.next();
            dealer.addCard(c4);

            // 3:Show player's card
            player.showHands();
            //and show only one card from the dealer's deck and keep the other one hidden
            dealer.showHands();

            boolean gameEnded = false;
            // 4:Loop to check if there's winner
            for (; ; ) {
                // 5:The sums of the cards of both player and deal decks are compared to check if there's winner
                if (player.getTotalValue() > 21) {
                    if (player.hasSecondValue()) {
                        if (player.getSecondTotalValue() > 21) {
                            System.out.println("You Busted!");
                            gameEnded = true;
                            break;
                        }
                    } else {
                        System.out.println("You Busted!");
                        gameEnded = true;
                        break;

                    }
                } else if (player.getTotalValue() == 21 && dealer.getTotalValue() == 21) {
                    dealer.showHands(true);
                    System.out.println("Push!");
                    gameEnded = true;
                    break;

                } else if (player.getTotalValue() == 21) {
                    if (dealer.hasSecondValue()) {
                        if (dealer.getSecondTotalValue() != 21) {
                            System.out.println("You win!");
                            gameEnded = true;
                            break;

                        }
                    } else {
                        System.out.println("You win!");
                        gameEnded = true;
                        break;
                    }
                }

                System.out.print("Enter Hit or Stay? ");
                String nextMove = input.nextLine();

                if (nextMove.equalsIgnoreCase("Hit")) {
                    Card c5 = d.next();
                    player.addCard(c5);

                    // display player hands
                    player.showHands();
                    continue;
                } else if (nextMove.equalsIgnoreCase("Stay")) {
                    player.showHands();
                    for (;;) {
                        // dealer needs continue to hit until its total value is bigger than 17 or busted or 21
                        if(dealer.getTotalValue() >= 21 || (dealer.hasSecondValue() && dealer.getSecondTotalValue() >= 21)) {
                            break;
                        }
                        if (dealer.getTotalValue() < 17 || (dealer.getTotalValue() > 21 && dealer.hasSecondValue() && dealer.getSecondTotalValue() < 17)) {
                            Card c6 = d.next();
                            dealer.addCard(c6);
                        } else {
                            break;
                        }
                    }
                    // force display dealer's all hands
                    dealer.showHands(true);
                }
                break;
            }

            // if game is not over, let us continue to check who is winner/loser
            if (!gameEnded) {
                if (player.getTotalValue() > 21) {
                    if (player.hasSecondValue()) {
                        if (player.getSecondTotalValue() > 21) {
                            System.out.println("You Busted!");
                            gameEnded = true;
                        }
                    } else {
                        System.out.println("You Busted!");
                        gameEnded = true;
                    }
                } else if (player.getTotalValue() == 21 && dealer.getTotalValue() == 21) {
                    System.out.println("Push!");
                    gameEnded = true;
                } else if (player.getTotalValue() == 21) {
                    if (dealer.hasSecondValue()) {
                        if (dealer.getSecondTotalValue() != 21) {
                            System.out.println("You win!");
                            gameEnded = true;

                        }
                    } else {
                        System.out.println("You win!");
                        gameEnded = true;

                    }
                } else if (dealer.getTotalValue() == 21) {
                    if (player.hasSecondValue()) {
                        if (player.getSecondTotalValue() != 21) {
                            System.out.println("You Lose!");
                            gameEnded = true;
                        }
                    } else {
                        System.out.println("You Lose!");
                        gameEnded = true;

                    }
                } else if (dealer.getTotalValue() > 21) {
                    if (dealer.hasSecondValue()) {
                        if (dealer.getSecondTotalValue() > 21) {
                            System.out.println("Dealer busted You win!");
                            gameEnded = true;

                        }
                    } else {
                        System.out.println("Dealer busted You win!");
                        gameEnded = true;

                    }
                }
            }

            if (!gameEnded) {

                // final decision  winner, loser or push
                if (player.getTotalValue() < 21 && dealer.getTotalValue() < 21) {
                    if (player.getTotalValue() > dealer.getTotalValue()) {
                        System.out.println("You win!");
                    } else if (player.getTotalValue() < dealer.getTotalValue()) {
                        System.out.println("You lose!");
                    } else if (player.getTotalValue() == dealer.getTotalValue()) {
                        System.out.println("Push!");
                    }
                } else if (player.hasSecondValue() && player.getTotalValue() > 21 && dealer.getTotalValue() < 21) {
                    if (player.getSecondTotalValue() > dealer.getTotalValue()) {
                        System.out.println("You win!");
                    } else if (player.getSecondTotalValue() < dealer.getTotalValue()) {
                        System.out.println("You lose!");
                    } else if (player.getSecondTotalValue() == dealer.getTotalValue()) {
                        System.out.println("Push!");
                    }
                } else if (dealer.hasSecondValue() && player.getTotalValue() < 21 && dealer.getTotalValue() > 21) {
                    if (player.getTotalValue() > dealer.getSecondTotalValue()) {
                        System.out.println("You win!");
                    } else if (player.getTotalValue() < dealer.getSecondTotalValue()) {
                        System.out.println("You lose!");
                    } else if (player.getTotalValue() == dealer.getSecondTotalValue()) {
                        System.out.println("Push!");
                    }
                } else if (player.hasSecondValue() && dealer.hasSecondValue() && player.getTotalValue() > 21 && dealer.getTotalValue() > 21) {
                    if (player.getSecondTotalValue() > dealer.getSecondTotalValue()) {
                        System.out.println("You win!");
                    } else if (player.getSecondTotalValue() < dealer.getSecondTotalValue()) {
                        System.out.println("You lose!");
                    } else if (player.getSecondTotalValue() == dealer.getSecondTotalValue()) {
                        System.out.println("Push!");
                    }
                }
            }

            System.out.print("\nPlay again? Y/N: ");
            String playAgain = input.nextLine();

            if (playAgain.equalsIgnoreCase("Y")) {
                continue;
            } else {
                break;
            }
        }

    }
}
