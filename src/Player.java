import java.util.ArrayList;

public class Player {

    protected String name;
    protected int playersNumber = 4;
    private final ArrayList<Card> cards = new ArrayList<>();

    public Player(String name, int playersNumber) {
        this.name = name;
        this.playersNumber = playersNumber;
    }

    public void giveCard(Card card) {
        cards.add(card);
    }
    public void dropCard(Card card) {
        cards.remove(card);
    }

    public boolean sayUno() {
        boolean uno = false;
        if (cards.size() ==1) {
            uno = true;
        } return uno;
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
        return "Player"+playersNumber +": "+ name;
    }
}
