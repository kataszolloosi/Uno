import java.io.PrintStream;
import java.util.ArrayList;

public class Player {

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
        return "Player"+playersNumber +": "+ name + " karten in Hand: "+cardsInHand;
    }
}
