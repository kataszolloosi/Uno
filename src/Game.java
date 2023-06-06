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
        //konstruktor
        playersInGame = new ArrayList<>();
        drawPile = new CardDeck();
        discardPile = new CardDeck();
    }



    public void start() {
        help.printHelp();  //help am anfang anzeigen

        shareCards();   //karten austeilen
        layStartCard();  //erste karte auf dem tisch
        chooseInitialPlayer();

    }

    public Player chooseInitialPlayer() {
        //wählt die erste spieler random
        Random initialPlayer = new Random();
        int randomIndex = initialPlayer.nextInt(3);

        setCurrentPlayerNumber(randomIndex);

        Player first = playersInGame.get(randomIndex);
        return first;
    }
    public Player currentPlayer() {
        Player currentPlayer = playersInGame.get(getCurrentPlayerNumber());
        return currentPlayer;
    }

    public void cardChoice() {
        do {
            Player currentPlayer = currentPlayer();
            output.println("\nPlayer " + currentPlayer.getName() + " your turn");
            penalty();
            if (canPlayerDropACard()) {
                output.println("Your cards: " + "\n" + currentPlayer.showMyCards());
                output.println("Which card do you want to play?");
                discardPile.addToDiscardPile(currentPlayer.playerDropCard());
                output.println("Card on Table: " + discardPile.getDropCard());
                colorChange();
            } else {
                output.println("Yout still don't have a card to play");
                output.println("\nCard on Table: "+discardPile.getDropCard() +"\n");
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

    public Card layStartCard() {
        //erste karte auf dem tisch
        Card card = new Card(null, null, 0);
        card = drawPile.drawCard();
        discardPile.addToDiscardPile(card);

//        if (card.getSign().equals("ColorChange") || card.getSign().equals("+4")) {
//            //wenn erste karte eine farbwahlkarte ist --> farbe zufällig gewählt wird
//            Random random = new Random();
//            String [] colors = {"Red", "Grenn", "Yellow", "Blue"};
//            int randomIndex = random.nextInt(colors.length);
//            String startColor = colors[randomIndex];
//            getDiscardPile().getDropCard().setColor(startColor);
//            output.println("Frist card is: " + card);
//            output.println("First color is: " + startColor);
//        }
        output.println("First card is: "+ card);
        return card;
    }

    public boolean cardValidation(Card card) {
        //prüft, ob ein karte ist valid oder nicht
        Card discardDeckCard = getDiscardPile().getDropCard();
        Player currentPlayer = currentPlayer();



        if (card.getColor().equals("Black")) {
            return true;
        } else if (discardDeckCard.getColor().equals(card.getColor()) || card.getColor().equals("Black") || discardDeckCard.getColor().equals("Black")) {
            //discardDeckCard.setColor(card.getColor());    // Neue Farbe auf das discardDeckCard-Objekt setzen
            return true;
        } else if (discardDeckCard.getSign().equals(card.getSign()) || card.getSign().equals("+4") || discardDeckCard.getSign().equals("+4")) {
            return true;
        } else if (discardDeckCard.getSign().equals(card.getSign()) || card.getSign().equals("ColorChange") || discardDeckCard.getSign().equals("ColorChange")) {
            //discardDeckCard.setColor(card.getColor());   // Neue Farbe auf das discardDeckCard-Objekt setzen
            return true;
        } else {
            output.println("Error... Choose another card!");
            output.println("Card on Table: " + discardDeckCard);
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
        //prüft, wie viel karte muss ein spieler heben
        Player currentPlayer = currentPlayer();
        Card discardDeckCard = getDiscardPile().getDropCard();

        if (discardDeckCard.getSign().equals("+2")) {
            output.println("But first you have to take 2 cards!");
            drawPenaltyCard();
            drawPenaltyCard();
            currentPlayer.showMyCards();
        } else if (discardDeckCard.getSign().equals("+4")) {
            output.println("But first you have to take 4 cards!");
            drawPenaltyCard();
            drawPenaltyCard();
            drawPenaltyCard();
            drawPenaltyCard();
            currentPlayer.showMyCards();
        } else {
        }
    }
    public void checkNextTurn() {
        //prüft, wer ist die nächste beim reverse, stop und beim normale karte
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
        //prüft, ob die karte ist eine normale karte
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

    public int isCardIsReverse() {
        //beim reverse karte prüft wer ist die nächste spieler
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
        //spielrichtung
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
        //wenn ein spieler bekommt ein +2 oder +4 karte, er muss abheben
        Player currentPlayer = currentPlayer();
        currentPlayer.giveCard(drawPile.drawCard());
    }

    public void colorChange() {
        //spieler kann farbe wählen
        Card discardDeckCard = getDiscardPile().getDropCard();
        if (discardDeckCard.getSign().equals("ColorChange")) {
            output.println("Give me a new color: ");
            String newColor = input.nextLine().toLowerCase();
            setNewColor(newColor);
        }
    }

    public boolean canPlayerDropACard() {
        //automatisch prüft, kann der spieler eine karte legen, oder muss aufheben
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
            output.println("Sorry, you dont have a card to play. You have to draw a card!");
            drawPenaltyCard();      //wenn der spieler hat keine richtige karte zum legen, zieht automatisch eine karte
            for (Card card : hand) {
                if (discardDeckCard.getColor().equals(card.getColor()) || discardDeckCard.getSign().equals(card.getSign())
                        || card.getColor().equals("Black") || card.getColor().equals(getNewColor())) {
                    hasCard = true;
                    break;
                }
            }
            output.println(currentPlayer.showMyCards());
        }
        return hasCard;
    }

    @Override
    public String toString() {
        return "Players=" + playersInGame;
    }
}
