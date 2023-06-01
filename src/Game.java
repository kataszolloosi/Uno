import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> playersInGame = new ArrayList<>();    //players in game list
    private CardDeck cardDeck = new CardDeck();   //original carddeck
    private CardDeck discardPile = new CardDeck();  //ablegestapel
    private Table table = new Table();
    private boolean clockweis;      //spielrichtung
    private int currentPlayerNumber = (int) (Math.random() * (4 - 1)) + 1; // random number (1-4);
    Scanner input = new Scanner(System.in);

    public CardDeck getDiscardPile() {
        return discardPile;
    }


    public void start() {
        System.out.println("----UNO----Kata-Nora-Marlies-Ksenija----\n");
        //spieler im main erstellen
        shareCards();   //karten austeilen
        layStartCard();  //erste karte auf dem tisch

    }

    public void cardChoice() {
        do {
            for (Player p : playersInGame) {
                System.out.println("\nPlayer " + p.getName() + " your turn");
                System.out.println("Your cards: " + "\n" + p.showMyCards());
                System.out.println("Which card do you want to play?");
                discardPile.addToCards(p.playerDropCard());
                System.out.println("Card on Table: " + discardPile.getDropCard());
            }
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
                p.giveCard(cardDeck.drawCard());  //eine karte von deck zu spieler
            }
        }
    }

    public void layStartCard() {
        //erste karte auf dem tisch
        //wenn erste karte ist farbwahlkarte - random farbe wird ausgewählt
        Card card = new Card(null, null, 0);
        card = cardDeck.drawCard();
        discardPile.addToCards(card);
    }

    public boolean cardValidation(Card card) {
        //Falls die Karte auf dem Tisch die gleiche Farbe oder Zeichen haben wird die Karte gelegt
        Card discardDeckCard = getDiscardPile().getDropCard();
        for (Player p : playersInGame) {
            if (card.getSign().equals("+2")) {
                System.out.println(p.getName() + " You must pick up 2 card");
                p.giveCard(cardDeck.drawCard());
                p.giveCard(cardDeck.drawCard());
            }
//            if (card.getSign().equals("Reverse")) {
//                System.out.println(p.getName()+" You are out, direction changed");
//                currentPlayerIndex--;
//            }
            if (card.getSign().equals("Stop")) {
                System.out.println(p.getName() + " You out in this turn");   //funktioniert noch nicht
                currentPlayerNumber = currentPlayerNumber + 2;
            }
            if (card.getSign().equals(+4)) {
                System.out.println(p.getName() + " You must pick up 4 card");
                p.giveCard((cardDeck.drawCard()));
                p.giveCard((cardDeck.drawCard()));
                p.giveCard((cardDeck.drawCard()));
                p.giveCard((cardDeck.drawCard()));
            }
            if (card.getSign().equals("ColorChange")) {
                System.out.println("Which color do you choose?");
                if (input.equals("Red")||input.equals("Yellow") || input.equals("Blue") ||input.equals("Green")) {
                    String choose = input.nextLine();
                    System.out.println(choose);
                    card.setColor(input.next());
                } else {
                    System.out.println("Error... You can only choose these colors: Red, Yellow, Green, Blue");
                }
            }

            if (card.getColor().equals("Black") || discardDeckCard.getColor().equals(card.getColor()) || discardDeckCard.getSign().equals(card.getSign())) {
                return true;
            } else {
                System.out.println("Error: choose the right card!");
                System.out.println("Card on Table: " + discardDeckCard);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Game: " + "\n" + " First Card: " + getDiscardPile().getDropCard() + "\t\n" +
                "\nPlayers=" + playersInGame;
    }
}
