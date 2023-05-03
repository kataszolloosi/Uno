import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);
    protected String name;
    protected int playersNumber = 4;
    private final ArrayList<Card> cardsInHand = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();

    public Player(String name, int playersNumber) {
        this.name = name;
        this.playersNumber = playersNumber;
    }

    //karte zu hand
    public void giveCard(Card card) {
        cardsInHand.add(card);
    }

    //karte von hand
    public void dropCard(Card card) {
        cardsInHand.remove(card);
    }

    //karte auf dem tisch legen
    public int playerDropCard() {
        int choice = input.nextInt();   //kann wählen welche karte(wievielte) vom reihe(1-7)
        for (int i = 0; i < cardsInHand.size(); i++) {
            if (choice>0 || choice < 8){
                cardsInHand.get(choice-1);
                dropCard(cardsInHand.get(choice-1));
            }
        } return choice;
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
