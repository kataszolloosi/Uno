import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();

    public void shareCards() {
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.giveCard(cards.get(i));
                cards.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", cards=" + cards +
                '}';
    }
}
