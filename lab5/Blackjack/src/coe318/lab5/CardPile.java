package coe318.lab5;

import java.util.ArrayList;
import java.util.Random;

public class CardPile {
    private ArrayList<Card> cards;

    public CardPile() {
        cards = new ArrayList<>();
    }
    //Adds a card to the card pile 
    public void add(Card card) {
        cards.add(card);
    }
    
    //Removes and returns a random card from the pile 
    public Card removeRandom() {
        if (!cards.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(cards.size());
            return cards.remove(index);
        }
        return null;
    }

    @Override
    public String toString() {
        //Creates a string representation of the card pile 
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(" ");
        }
        return sb.toString();
    }
    //Gets the list 
    public ArrayList<Card> getCards() {
        return cards;
    }

    public static void main(String[] args) {
        
        //Add cards to the pile and then removes and prints the random cards 
        CardPile p = new CardPile();
        p.add(new Card(2, 1, true));
        p.add(new Card(3, 2, true));
        p.add(new Card(4, 3, false));
        p.add(new Card(14, 1, true));
        System.out.println("Removed: " + p.removeRandom());
        System.out.println("Removed: " + p.removeRandom());
        System.out.println("Removed: " + p.removeRandom());
        System.out.println("Removed: " + p.removeRandom());
        System.out.println();

        CardPile deck = new CardPile();
        
        //A standard deck of 52 cards is created and theyre printed in random order 
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(i, j, true));
            }
        }
        for (int i = 0; i < 52; i++) {
            System.out.println((i + 1) + ": " + deck.removeRandom());
        }
    }
}
