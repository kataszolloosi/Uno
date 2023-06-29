import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Scanner input = new Scanner(System.in);
    private static PrintStream output = new PrintStream(System.out);
    private static ArrayList<Player> playersInGame;    //players in game list
    private static CardDeck drawPile;   //ziehstapel
    private static CardDeck discardPile;  //ablegestapel
    private Help help = new Help();
    private boolean clockwise = true;      //spielrichtung
    private static int currentPlayerNumber;
    private Player winner;
    private boolean gameOver;
    //    private int round = 1;
//    private int session = 1;
//    private boolean exit = false;
    private static String newColor;


    public static String getNewColor() {
        return newColor;
    }

    public static String setNewColor(String newColor) {
        newColor = newColor;
        return newColor;
    }

    public static CardDeck getDiscardPile() {
        return discardPile;
    }

    public static int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    public Game() {
        //konstruktor
        playersInGame = new ArrayList<>();
        drawPile = new CardDeck();
        drawPile.createCards();
        System.out.println("größe von drawpile: " + drawPile.getNumberOfCards());
        discardPile = new CardDeck();
        System.out.println("größe von discardpile: " + discardPile.getNumberOfCards());
    }


    public void start() {
        //help.printHelp();  //help am anfang anzeigen

        drawPile.createCards();
        System.out.println("Discardpile: ");
        discardPile.showAllCards();
        drawPile.shuffle();
//        drawPile.showAllCards();
        addPlayers();


        shareCards();   //karten austeilen
        layStartCard();  //erste karte auf dem tisch
        chooseInitialPlayer();
        cardChoice();
        winner();

    }

    public Player chooseInitialPlayer() {
        //wählt die erste spieler random
        Random initialPlayer = new Random();
        int randomIndex = initialPlayer.nextInt(3);

        setCurrentPlayerNumber(randomIndex);

        Player first = playersInGame.get(randomIndex);
        return first;
    }

    public static Player currentPlayer() {
        Player currentPlayer = playersInGame.get(getCurrentPlayerNumber());
        return currentPlayer;
    }

    public void addPlayers() {
        System.out.println("Please enter number of players: ");
        int num = input.nextInt();

        while (num < 1 || num > 4) {
            System.out.println("Max 4 players are allowed. Please choose between 1 and 4");
            num = input.nextInt();
        }
        input.nextLine();
        for (int i = 0; i < num; i++) {
            System.out.println("Enter your name: ");
            String name = input.nextLine();
            Player player = new Player(name, getCurrentPlayerNumber());
            output.println("Hello " + name);
            playersInGame.add(player);
        }
        System.out.println("Number of human players: " + playersInGame.size() + "\n");
        int botSize = 4 - playersInGame.size();
        for (int i = 0; i < botSize; i++) {
            String name = "Bot " + (i + 1);
            Bot bot = new Bot(name, getCurrentPlayerNumber());
            playersInGame.add(bot);
            System.out.println(name + " will be joining you as well");
        }
    }

    public void cardChoice() {

        //prüft ob der player kann eine karte legen und welche, wenn er kann nicht, zieht eine karte
        do {
            Player currentPlayer = currentPlayer();
            output.println("\nPlayer " + currentPlayer.getName() + " your turn");
            System.out.println("0 - Help");

            penalty();
            if (canPlayerDropACard()) {
                output.println("Your cards: " + "\n" + currentPlayer.showMyCards());
                output.println("Which card do you want to play?");
                discardPile.addToPile(currentPlayer.playerDropCard());
                output.println("Card on Table: " + discardPile.getDropCard());
            } else {
                output.println("Yout still don't have a card to play");
                output.println("\nCard on Table: " + discardPile.getDropCard() + "\n");
            }
            checkNextTurn();
        } while (gameOver != true);
    }

    public void shareCards() {
        //karten austeilen - 7karte
        for (Player p : playersInGame) {
            for (int i = 0; i < 7; i++) {
                System.out.println("karten in drawpile: " + drawPile.getNumberOfCards());
                p.addCardToHand(drawPile.drawCard());//eine karte von deck zu spieler

            }
        }
    }

    //eigene funktion wo kann ich neu mischen

    public Card layStartCard() {
        //erste karte auf dem tisch, wenn es ist +4, dann wird die farbe random ausgewählt
        Card card = getDiscardPile().getDropCard();

        if (card.getSign().equals("ColorChange") || card.getSign().equals("+4")) {
            // Wenn die erste Karte eine Farbwahlkarte ist, wird die Farbe zufällig ausgewählt
            Random random = new Random();
            String[] colors = {"Red", "Green", "Yellow", "Blue"};
            int randomIndex = random.nextInt(colors.length);
            String startColor = colors[randomIndex];
            setNewColor(startColor);
            newColor = card.getColor();
            output.println("First card is: " + card);
            output.println("First color is: " + startColor);
        } else {

            output.println("First card is: " + card);
        }

        return card;
    }

    public static boolean cardValidation(Card discardedCard) {
        Card cardOnTheTable = getDiscardPile().getDropCard();
        Player currentPlayer = currentPlayer();

        if (discardedCard.getColor().equals("Black")) {
            if (discardedCard.getSign().equals("+4") || discardedCard.getSign().equals("ColorChange")) {
                setColorIfColorChangeCard(discardedCard);
                output.println("new color: " + newColor);
                return true;
            }
        } else if (discardedCard.getColor().equals(newColor)) {
            return true;
        } else if (discardedCard.getColor().equals(cardOnTheTable.getColor())) {
            return true;
        } else if (discardedCard.getSign().equals(cardOnTheTable.getSign())) {
            return true;
        } else {
            output.println("Error... Choose another card!");
            output.println("Card on Table: " + cardOnTheTable);
            System.out.println("discardpile");
            discardPile.showAllCards();
        }
        penalty();
        return false;
    }

    public static void penalty() {
        //prüft, wie viel karte muss ein spieler heben
        Player currentPlayer = currentPlayer();
        Card discardDeckCard = getDiscardPile().getDropCard();

        if (discardDeckCard.getSign().equals("+2")) {
            output.println("But first you have to take 2 cards!");
            drawPenaltyCard();
            drawPenaltyCard();
        } else if (discardDeckCard.getSign().equals("+4")) {
            output.println("But first you have to take 4 cards!");
            drawPenaltyCard();
            drawPenaltyCard();
            drawPenaltyCard();
            drawPenaltyCard();
        } else {
        }
    }

    public void checkNextTurn() {
        //prüft, wer ist die nächste beim reverse, stop und beim normale karte
        Card discardDeckCard = getDiscardPile().getDropCard();
        if (discardDeckCard.getSign().equals("Reverse")) {
            isCardIsReverse();
            System.out.println("Switch direction ");
        } else if (discardDeckCard.getSign().equals("Stop")) {
            isCardStop();
            System.out.println("You are out " + playersInGame.get(currentPlayerNumber - 1).getName());
        } else {
            isCardNormal();
        }
    }

    public int isCardNormal() {
        //prüft, ob die karte ist eine normale karte
        int currentPlayerIndex = getCurrentPlayerNumber();

        if (currentPlayerIndex == 0) {
            if (clockwise) {
                currentPlayerIndex++;
            } else {
                currentPlayerIndex = 3;
            }
        } else if (currentPlayerIndex == 3) {
            if (clockwise) {
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex = 2;
            }
        } else {
            if (clockwise) {
                currentPlayerIndex++;
            } else {
                currentPlayerIndex--;
            }
        }
        setCurrentPlayerNumber(currentPlayerIndex);
        return currentPlayerIndex;
    }

    public int isCardIsReverse() {
        //beim reverse karte prüft wer ist die nächste spieler
        int currentPlayerIndex = getCurrentPlayerNumber();

        if (currentPlayerIndex == 0) {
            if (clockwise) {
                currentPlayerIndex = 3;
                clockwise = false;
            } else {
                currentPlayerIndex = 1;
                clockwise = true;
            }
        } else if (currentPlayerIndex == 3) {
            if (clockwise) {
                currentPlayerIndex = 2;
                clockwise = false;
            } else {
                currentPlayerIndex = 0;
                clockwise = true;
            }
        } else {
            if (clockwise) {
                currentPlayerIndex--;
                clockwise = false;
            } else {
                currentPlayerIndex++;
                clockwise = true;
            }
        }
        setCurrentPlayerNumber(currentPlayerIndex);
        return currentPlayerIndex;
    }

    public int isCardStop() {
        //spielrichtung
        int currentPlayerIndex = getCurrentPlayerNumber();

        if (currentPlayerIndex == 0) {
            if (clockwise) {
                currentPlayerIndex = 2;
            } else {
                currentPlayerIndex = 2;
            }
        } else if (currentPlayerIndex == 3) {
            if (clockwise) {
                currentPlayerIndex = 1;
            } else {
                currentPlayerIndex = 1;
            }
        } else if (currentPlayerIndex == 1) {
            if (clockwise) {
                currentPlayerIndex = 3;
            } else {
                currentPlayerIndex = 3;
            }
        } else if (currentPlayerIndex == 2) {
            if (clockwise) {
                currentPlayerIndex = 0;
            }
        } else {
            if (clockwise) {
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex = 0;
            }
        }
        setCurrentPlayerNumber(currentPlayerIndex);
        return currentPlayerIndex;
    }

    public static void drawPenaltyCard() {
        //wenn ein spieler bekommt ein +2 oder +4 karte, er muss abheben
        Player currentPlayer = currentPlayer();
        currentPlayer.addCardToHand(drawPile.drawCard());
    }

    public boolean canPlayerDropACard() {
        //automatisch prüft, kann der spieler eine karte legen, oder muss aufheben
        Card discardDeckCard = getDiscardPile().getDropCard();
        Player currentPlayer = currentPlayer();
        ArrayList<Card> hand = currentPlayer.getCardsInHand();
        boolean hasCard = false;

        for (Card card : hand) {
            if ((discardDeckCard.getColor().equals(card.getColor()) || discardDeckCard.getSign().equals(card.getSign())
                    || card.getColor().equals("Black") || card.getColor().equals(getNewColor())) && hand != null) {
                hasCard = true;
                break;
            }
        }

        if (!hasCard) {
            output.println("Sorry, you dont have a card to play. You have to draw a card!");
            drawPenaltyCard();      //wenn der spieler hat keine richtige karte zum legen, zieht automatisch eine karte
            for (Card card : hand) {
                if (discardDeckCard.getColor().equals(card.getColor()) || discardDeckCard.getSign().equals(card.getSign())
                        || card.getColor().equals("Black") || card.getColor().equals(getNewColor())) {
                    hasCard = true;
                    break;
                }
            }
        }
        return hasCard;
    }

    public static void setColorIfColorChangeCard(Card cardOnTheTable) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Choose a color: Red, Blue, Green, Yellow");
            String colorWish = input.nextLine();
            if (colorWish.equalsIgnoreCase("Red")) {
                System.out.println("You wish for Red");
                cardOnTheTable.setColor("Red");
                newColor= "Red";
                return;
            } else if (colorWish.equalsIgnoreCase("Blue")) {
                System.out.println("You wish for Blue");
                cardOnTheTable.setColor("Blue");
                newColor= "Blue";
                return;
            } else if (colorWish.equalsIgnoreCase("Green")) {
                System.out.println("You wish for Green");
                cardOnTheTable.setColor("Green");
                newColor= "Green";
                return;
            } else if (colorWish.equalsIgnoreCase("Yellow")) {
                System.out.println("You wish for Yellow");
                cardOnTheTable.setColor("Yellow");
                newColor= "Yellow";
                return;
            } else {
                System.out.println("This color is not valid!");
            }
        } while (true);
    }

    public Player winner() {
        if (currentPlayer().getCardsInHand().size() == 0) {
            winner = currentPlayer();
            gameOver = true;
        }
        return winner;
    }

//    public static void resetDrawPile() {
//        //discardpile erstellen von discarded cards
//        drawPile.clear();
//        for (Card discardedCard : discardPile) {
//            discardPile.addToPile(discardedCard);
//        }
//        discardPile.clear();
//        discardPile.shuffle();
//    }

    @Override
    public String toString() {
        return "Players=" + playersInGame;
    }
}