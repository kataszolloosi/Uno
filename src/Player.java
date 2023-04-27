import java.util.ArrayList;

public class Player {

    private String name;
    private int playersNumber = 4;
    public ArrayList<Card> cards = new ArrayList<>();

    public Player(String name, int playersNumber) {
        this.name = name;
        this.playersNumber = playersNumber;
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


    public void giveCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return "Player"+playersNumber +": "+ name;
    }
}
