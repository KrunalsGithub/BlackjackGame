package coe318.lab5;

public class BlackjackGame {
    //declaring variables
    private CardPile deck;
    private CardPile houseCards;
    private CardPile yourCards;
    private boolean houseDone;
    private boolean playerDone;
    private UserInterface ui;

    public BlackjackGame(UserInterface ui) {
        this.ui = ui;
        ui.setGame(this);
        deck = new CardPile(); //The deck of cards is initialized for the game
        //The deck is initalized to have all 52 cards
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(i, j, true));
            }
        }
        houseCards = new CardPile();
        yourCards = new CardPile();
        houseDone = false;
        playerDone = false;
    }

    public void start() {
        Card c;
        //The game is started by dealing the initial cards
        c = deck.removeRandom();
        c.setFaceUp(false);
        getHouseCards().add(c);
        getHouseCards().add(deck.removeRandom());
        getYourCards().add(deck.removeRandom());
        getYourCards().add(deck.removeRandom());
        //The initial state of the game is displayed
        ui.display();
    }

    public void play() {
        //The game continues until the house and the player are both finished
        while (!houseDone || !playerDone) {
            if (!houseDone) {
                //Now its the house's turn to play 
                if (score(getHouseCards()) <= 17) {
                    //The House hits if its score is less than or equal to 17 
                    getHouseCards().add(deck.removeRandom());
                    ui.display();
                } else {
                    houseDone = true;
                }
            }
            if (!playerDone) {
                //Now its the players turn to decide whether to hit or stand 
                if (ui.hitMe()) {
                    //The player hits if they choose to continue 
                    getYourCards().add(deck.removeRandom());
                    ui.display();
                } else {
                    playerDone = true;
                }
            }
        }
    }

    public void end() {
        //Reveals the first card of the house 
        getHouseCards().getCards().get(0).setFaceUp(true);
        ui.display();
        //Determine the game outcome and displays it
        ui.gameOver();
    }

    /**
     * Determine the score of a pile of cards.
     *
     * @param p
     * @return the score
     */
    public int score(CardPile p) {
        int totalScore = 0;
        int numAces = 0;

        for (Card card : p.getCards()) {
            int rank = card.getRank();

            if (rank == 14) {  // Ace
                totalScore += 11;
                numAces++;
            } else if (rank >= 11 && rank <= 13) {  // Jack, Queen, King
                totalScore += 10;
            } else {
                totalScore += rank;
            }
        }

        // Handle Aces - if they cause a bust, its treated as 1 
        while (numAces > 0 && totalScore > 21) {
            totalScore -= 10;
            numAces--;
        }

        return totalScore;
    }

    /**
     * Get the house cards pile.
     *
     * @return the houseCards
     */
    public CardPile getHouseCards() {
        return houseCards;
    }

    /**
     * Get the player's cards pile.
     *
     * @return the yourCards
     */
    public CardPile getYourCards() {
        return yourCards;
    }

    public static void main(String[] args) {
        //Entry point for the program 
        BlackjackGame game = new BlackjackGame(new SimpleUI());
        game.start();
        game.play();
        game.end();
    }
}
