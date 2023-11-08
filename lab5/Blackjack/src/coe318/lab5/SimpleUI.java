package coe318.lab5;

import java.util.Scanner;

public class SimpleUI implements UserInterface {
    private BlackjackGame game;

    @Override
    public void setGame(BlackjackGame game) {
        this.game = game;
    }

    @Override
    public void display() {
        // Displays the house's cards
        System.out.println("House cards: " + game.getHouseCards());

        // Displays the player's (your) cards
        System.out.println("Your cards: " + game.getYourCards());

        // Displays the player's (your) score
        System.out.println("Your score: " + game.score(game.getYourCards()));
    }

    @Override
    public boolean hitMe() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want another card? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        /*If the player enters the "yes" value, it returns true and indicates that they want to hit
        otherwise it returns false*/
        return input.equals("yes");
    }

    @Override
    public void gameOver() {
        // Displays the final cards, scores, as well  the winner
        System.out.println("House cards: " + game.getHouseCards()); 
        System.out.println("Your cards: " + game.getYourCards()); 
        int houseScore = game.score(game.getHouseCards()); 
        int playerScore = game.score(game.getYourCards());

        System.out.println("House score: " + houseScore);
        System.out.println("Your score: " + playerScore);
        
        /*The winner is determined finally based on the game rules.
        There will either be a winner or a tie and the result is displayed based
        on the final result*/
        if (playerScore > 21) {
            System.out.println("You went over 21. House wins!");
        } else if (houseScore > 21 || playerScore > houseScore) {
            System.out.println("You win!");
        } else if (playerScore == houseScore) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("House wins!");
        }
    }
}
