import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    ArrayList<Player> players = new ArrayList<>();
    CardDeck cardDeck = new CardDeck();

    public void addPlayerToGame(Player p) {
        players.add(p);
        }

    public void addCardsToGame(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

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
