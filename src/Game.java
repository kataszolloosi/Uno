import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    private PrintStream output = new PrintStream(System.out);
    private ArrayList<Player> playersInGame;    //players in game list
    private CardDeck drawPile;   //ziehstapel
    private CardDeck discardPile;  //ablegestapel
    private Table table = new Table();
    private Help help = new Help();
    private boolean clockweis = true;      //spielrichtung
    private int currentPlayerNumber;
//    private int round = 1;
//    private int session = 1;
//    private boolean exit = false;
    private String newColor;


    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public CardDeck getDiscardPile() {
        return discardPile;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    public Game() {
        playersInGame = new ArrayList<>();
        drawPile = new CardDeck();
        discardPile = new CardDeck();
        currentPlayerNumber = (int) (Math.random() * (4 - 1)) + 1;  //random number 1-4
    }

    public Player currentPlayer() {
        Player currentPlayer = playersInGame.get(getCurrentPlayerNumber());
        return currentPlayer;
    }

    public void start() {
        //initialize();
        System.out.println("\n----UNO----Kata-Nora-Marlies-Ksenija----\n");
        shareCards();   //karten austeilen
        layStartCard();  //erste karte auf dem tisch
        chooseInitialPlayer();

    }

    public Player chooseInitialPlayer() {
        Random initialPlayer = new Random();
        int randomIndex = initialPlayer.nextInt(3);

        setCurrentPlayerNumber(randomIndex);

        Player first = playersInGame.get(randomIndex);
        return first;
    }

//    private void initialize() {
//        exit = false;
//        if (round > 1) {
//            //neue Runde
//        } else { //beim ersten spiel
//            help.printHelp();
//
//            //createPlayer();
//            //drawpile erstellen
//            //currentPlayer initialize
//            currentPlayer = playersInGame.get(currentPlayerNumber - 1);
//            output.println("Startplayer: " + currentPlayer);
//
//        }
//
//    }

    public void cardChoice() {
        do {
            Player currentPlayer = currentPlayer();
            System.out.println("\nPlayer " + currentPlayer.getName() + " your turn");
            penalty();
            if (canPlayerDropACard()) {
                System.out.println("Your cards: " + "\n" + currentPlayer.showMyCards());
                System.out.println("Which card do you want to play?");
                discardPile.addToDiscardPile(currentPlayer.playerDropCard());
                System.out.println("Card on Table: " + discardPile.getDropCard());
                colorChange();
            } else {
                System.out.println("Yout still don't have a card to play");
                System.out.println("\nCard on Table: "+discardPile.getDropCard() +"\n");
            }
            checkNextTurn();
        } while (table != null);
    }

    public void addPlayerToPlayerList(Player p) {
        //players to list
        playersInGame.add(p);
    }

    public void shareCards() {
        //karten austeilen - 7karte
        for (Player p : playersInGame) {
            for (int i = 0; i < 7; i++) {
                p.giveCard(drawPile.drawCard());  //eine karte von deck zu spieler
            }
        }
    }

    public void layStartCard() {
        //erste karte auf dem tisch
        Card card = new Card(null, null, 0);
        card = drawPile.drawCard();
        discardPile.addToDiscardPile(card);
    }

    public boolean cardValidation(Card card) {
        //Falls die Karte auf dem Tisch die gleiche Farbe oder Zeichen haben wird die Karte gelegt
        Card discardDeckCard = getDiscardPile().getDropCard();
        Player currentPlayer = currentPlayer();

        System.out.println("CardValidation: I got card " + card);
        System.out.println("Discardpile dropcard: " + discardDeckCard);
        System.out.println("Color: '" + card.getColor() + "'");
        if (card.getColor().equals("Black")) {
            return true;
        } else if (discardDeckCard.getColor().equals(card.getColor()) || card.getColor().equals("Black") || discardDeckCard.getColor().equals("Black")) {
            return true;
        } else if (discardDeckCard.getSign().equals(card.getSign()) || card.getSign().equals("+4") || discardDeckCard.getSign().equals("+4")) {
            return true;
        } else if (discardDeckCard.getSign().equals(card.getSign()) || card.getSign().equals("ColorChange") || discardDeckCard.getSign().equals("ColorChange")) {
            return true;
        } else {
            System.out.println("Error... Choose another card!");
            System.out.println("Card on Table: " + discardDeckCard);
        }
        if (discardDeckCard.getSign().equals("+2") || discardDeckCard.getSign().equals("+4")) {
            if (discardDeckCard.getSign().equals("+2")) {
                currentPlayer.giveCard(card);
                currentPlayer.giveCard(card);
                discardPile.addToDiscardPile(card);
                discardPile.addToDiscardPile(card);
            } else if (discardDeckCard.getSign().equals("+4")) {
                currentPlayer.giveCard(card);
                currentPlayer.giveCard(card);
                currentPlayer.giveCard(card);
                currentPlayer.giveCard(card);
                discardPile.addToDiscardPile(card);
                discardPile.addToDiscardPile(card);
                discardPile.addToDiscardPile(card);
                discardPile.addToDiscardPile(card);
            }
        }
        return false;
    }

    public void penalty() {
        Player currentPlayer = currentPlayer();
        Card discardDeckCard = getDiscardPile().getDropCard();

        if (discardDeckCard.getSign().equals("+2")) {
            System.out.println("But first you have to take 2 cards!");
            drawPenaltyCard();
            drawPenaltyCard();
            currentPlayer.showMyCards();
        } else if (discardDeckCard.getSign().equals("+4")) {
            System.out.println("But first you have to take 4 cards!");
            drawPenaltyCard();
            drawPenaltyCard();
            drawPenaltyCard();
            drawPenaltyCard();
            currentPlayer.showMyCards();
        } else {
        }
    }
    public void checkNextTurn() {
        Card discardDeckCard = getDiscardPile().getDropCard();
        if (discardDeckCard.getSign().equals("Reverse")) {
            isCardIsReverse();
        } else if (discardDeckCard.getSign().equals("Stop")) {
            isCardStop();
        } else {
            isCardNormal();
        }
    }

    public int isCardNormal() {
        int currentPlayerIndex = getCurrentPlayerNumber();

        if (currentPlayerIndex == 0) {
            if (clockweis) {
                currentPlayerIndex++;
            } else {
                currentPlayerIndex = 3;
            }
        } else if (currentPlayerIndex == 3) {
            if (clockweis) {
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex = 2;
            }
        } else {
            if (clockweis) {
                currentPlayerIndex++;
            } else {
                currentPlayerIndex--;
            }
        }
        setCurrentPlayerNumber(currentPlayerIndex);
        return currentPlayerIndex;
    }

    public int isCardIsReverse() { //This method is to decide who has the next turn when the card "Reverse" is played
        int currentPlayerIndex = getCurrentPlayerNumber();

        if (currentPlayerIndex == 0) {
            if (clockweis) {
                currentPlayerIndex = 3;
                clockweis = false;
            } else {
                currentPlayerIndex = 1;
                clockweis = true;
            }
        } else if (currentPlayerIndex == 3) {
            if (clockweis) {
                currentPlayerIndex = 2;
                clockweis = false;
            } else {
                currentPlayerIndex = 0;
                clockweis = true;
            }
        } else {
            if (clockweis) {
                currentPlayerIndex--;
                clockweis = false;
            } else {
                currentPlayerIndex++;
                clockweis = true;
            }
        }
        setCurrentPlayerNumber(currentPlayerIndex);
        return currentPlayerIndex;
    }
    public int isCardStop() {
        int currentPlayerIndex = getCurrentPlayerNumber();

        if (currentPlayerIndex == 0) {
            if (clockweis) {
                currentPlayerIndex = 2;
            } else {
                currentPlayerIndex = 2;
            }
        } else if (currentPlayerIndex == 3) {
            if (clockweis) {
                currentPlayerIndex = 1;
            } else {
                currentPlayerIndex = 1;
            }
        } else if (currentPlayerIndex == 1)  {
            if (clockweis) {
                currentPlayerIndex = 3;
            } else {
                currentPlayerIndex = 3;
            }
        }else {
            if (clockweis) {
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex = 0;
            }
        }
        setCurrentPlayerNumber(currentPlayerIndex);
        return currentPlayerIndex;
    }

    public void drawPenaltyCard() {
        Player currentPlayer = currentPlayer();
        currentPlayer.giveCard(drawPile.drawCard());
    }

    public void colorChange() {
        Card discardDeckCard = getDiscardPile().getDropCard();
        if (discardDeckCard.getSign().equals("ColorChange")) {
            System.out.println("Give me a new color: ");
            String newColor = input.nextLine();
            setNewColor(newColor);
        }

    }

    public boolean canPlayerDropACard() {
        Card discardDeckCard = getDiscardPile().getDropCard();
        Player currentPlayer = currentPlayer();
        ArrayList<Card> hand = currentPlayer.getCardsInHand();
        boolean hasCard = false;

        for (Card card : hand) {
            if (discardDeckCard.getColor().equals(card.getColor()) || discardDeckCard.getSign().equals(card.getSign())
                    || card.getColor().equals("Black") || card.getColor().equals(getNewColor())) {
                hasCard = true;
                break;
            }
        }

        if (!hasCard) {
            System.out.println("Sorry, you dont have a card to play. You have to draw a card!");
            drawPenaltyCard();
            for (Card card : hand) {
                if (discardDeckCard.getColor().equals(card.getColor()) || discardDeckCard.getSign().equals(card.getSign())
                        || card.getColor().equals("Black") || card.getColor().equals(getNewColor())) {
                    hasCard = true;
                    break;
                }
            }
            System.out.println(currentPlayer.showMyCards());
        }
        return hasCard;
    }

    @Override
    public String toString() {
        return "Game: " + "\n" + " First Card: " + getDiscardPile().getDropCard() + "\t\n" +
                "\nPlayers=" + playersInGame;
    }
}
