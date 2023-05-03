import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    ArrayList<Player> playersInGame = new ArrayList<>();
    private CardDeck cardDeck = new CardDeck();
    ArrayList<Card> cardsInGame = new ArrayList<>();


    public void addPlayerToPlayerList(Player p) {
        playersInGame.add(p);
    }


    //karten austeilen - 7karte
        public void shareCards() {
            for (Player p : playersInGame) {
                for (int i = 0; i < 7; i++) {
                    p.giveCard(cardDeck.drawCard());  //eine karte von deck zu spieler
                }
            }
        }


        @Override
        public String toString() {
            return "Game{" +
                    "players=" + playersInGame +
                    '}';
        }
}
