import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Scanner input = new Scanner(System.in);
    protected String name;
    protected int playersNumber;
    private final ArrayList<Card> cardsInHand = new ArrayList<>();


    public Player(String name, int playersNumber) {
        this.name = name;
        this.playersNumber = playersNumber;
    }

    public void addCardToHand(Card card) {
        //zieht eine Karte vom Kartenstapel (CardDeck) und fügt sie der Hand des Spielers hinzu
        cardsInHand.add(card);
    }

    public Card playerDropCard() {
        //entfernt eine Karte aus der Hand des Spielers anhand des Indexes und gibt sie zurück
        // Dadurch kann der Spieler eine Karte spielen

        int choice;   //kann wählen welche karte(wievielte) vom reihe(1-7)

        do {
            String a = input.nextLine();
            try {
                choice = Integer.parseInt(a);

            } catch (NumberFormatException nfe) {
                System.out.println("Error!...  Please enter a NUMBER between 1 and " + cardsInHand.size());
                continue;
            }
            if (choice > 0 && choice <= cardsInHand.size()) {
                if (Game.cardValidation(cardsInHand.get(choice - 1))) {
                    return cardsInHand.remove(choice - 1);
                }
            } else {
                System.out.println("Error... Please enter a NUMBER between 1 and " + cardsInHand.size() + " eingeben:");
            }
        }
        while (true);
    }

    public String showMyCards() {
        //welche karten hat der spieler
        String myCards = "";
        int i = 1;
        for (Card shoMyCards : cardsInHand) {
            myCards += i + " -> " + shoMyCards.toString() + "\n";
            i++;
        }
        return myCards;
    }

    public boolean hasWon() {
        return cardsInHand.isEmpty();
    }

    //getter-setter methode
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPlayersNumber() {
        return playersNumber;
    }
    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    @Override
    public String toString() {

        return "Player" + playersNumber + ": " + name + " Karten in Hand: " + cardsInHand + "\n";
    }
}
