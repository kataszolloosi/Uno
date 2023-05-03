import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);
    protected String name;
    protected int playersNumber = 4;
    private final ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    public Player(String name, int playersNumber) {
        this.name = name;
        this.playersNumber = playersNumber;
    }

    //karte zu hand
    public void giveCard(Card card) {
        cardsInHand.add(card);
    }


    //karte auf dem tisch legen
    public Card playerDropCard() {
        int choice = input.nextInt();   //kann wählen welche karte(wievielte) vom reihe(1-7)
        if (choice > 0 || choice < 8) {
            return cardsInHand.remove(choice - 1);
        }
        return null;
    }
//    public boolean sayUno() {
//        boolean uno = false;
//        if (cards.size() ==1) {
//            uno = true;
//        } return uno;
//    }

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
        return "Player"+playersNumber +": "+ name + " Karten in Hand: "+cardsInHand + "\n";
    }
}
