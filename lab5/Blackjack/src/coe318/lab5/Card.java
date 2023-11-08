package coe318.lab5;

public class Card implements Comparable<Card> {
    // Symbolic constants for the suits in a deck of cards
    public static final int CLUB = 0;
    public static final int DIAMOND = 1;
    public static final int HEART = 2;
    public static final int SPADE = 3;

    private int rank; //Represents the rank of the card (2-14, where 11-14 represents Jack, Queen, King and Ace respectively)
    private int suit; //Represents of the suit of the card (0-3 representing clubs, diamonds, hearts and spades)
    private boolean faceUp; //Represents whether the card is facing up or down, hence why its in boolean)

    //A constructor to create a new card with a given rank, suit and its state of facing up or down)
    public Card(int rank, int suit, boolean faceUp) {
        this.rank = rank; //Set the rank of the card (2-14)
        this.suit = suit; //Sets the suit of the card (0-3)
        this.faceUp = faceUp; //Sets the status of the card (facing up or down)
    }

    public boolean isFaceUp() {
        //Checks if the card is facing up 
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        //Sets the face up status of the card
        this.faceUp = faceUp;
    }
    
    //Gets the rank of the card
    public int getRank() {
        return rank;
    }

    //Gets the suit of the card
    public int getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object ob) {
        //Compare if this card is equal to another card based on its rank and suit 
        if (!(ob instanceof Card)) {
            return false;
        }
        Card c = (Card) ob;
        return this.rank == c.rank && this.suit == c.suit;
    }

    @Override
    public int hashCode() {
        //Generates hash code for the card based on rank and suit
        int hash = 7;
        hash = 31 * hash + this.rank;
        hash = 31 * hash + this.suit;
        return hash;
    }

    @Override
    public int compareTo(Card c) {
        //Compares this card to another card for sorting purposes 
        if (this.rank == c.rank) {
            return this.suit - c.suit;
        }
        return this.rank - c.rank;
    }

    public String getRankString() {
        //Gets the string representation of the card's rank (e.g. 2, jack, queen)
        if (rank >= 2 && rank <= 10) {
            return String.valueOf(rank);
        } else if (rank == 11) {
            return "Jack";
        } else if (rank == 12) {
            return "Queen";
        } else if (rank == 13) {
            return "King";
        } else if (rank == 14) {
            return "Ace";
        } else {
            return "Invalid";
        }
    }

    public String getSuitString() {
        //Gets the string representation of the card's suit (e.g. clubs, spades) 
        switch (suit) {
            case CLUB:
                return "Clubs";
            case DIAMOND:
                return "Diamonds";
            case HEART:
                return "Hearts";
            case SPADE:
                return "Spades";
            default:
                return "Invalid";
        }
    }

    @Override
    public String toString() {
        //Generates a string representation of the card (e.g. 3 of hearts or ?)
        if (faceUp) {
            return getRankString() + " of " + getSuitString();
        } else {
            return "?"; //Represents a face down card
        }
    }

  public static void main(String[] args) {
    //Create 5 of clubs
    Card club5 = new Card(5, 0, true);
    System.out.println("club5: " + club5);
    Card spadeAce = new Card(14, SPADE, true);
    System.out.println("spadeAce: " + spadeAce);
    System.out.println("club5 compareTo spadeAce: "
            + club5.compareTo(spadeAce));
    System.out.println("club5 compareTo club5: "
            + club5.compareTo(club5));
    System.out.println("club5 equals spadeAce: "
            + club5.equals(spadeAce));
    System.out.println("club5 equals club5: "
            + club5.equals(club5));
  }
}
