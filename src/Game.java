import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    ArrayList<Player> playersInGame = new ArrayList<>();
    private CardDeck cardDeck = new CardDeck();
    ArrayList<Card> cardsInGame = new ArrayList<>();


    public void start() {
        shareCards();   //karten austeilen
        layStartCard();  //erste karte auf dem tisch
        cardChoice();
    }

   public String cardChoice() {
        String choice = "Welche karte möchten Sie ausspielen?";
       System.out.println(choice);
        return choice;
   }


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

    //erste karte auf dem tisch
    public Card layStartCard() {
        Card card = new Card(null, null);
        card = cardDeck.drawCard();
        return card;
    }

    @Override
    public String toString() {
        return "Game: " +  "\n" + " First Card: " + layStartCard()+ "\n" +
                "Players=" + playersInGame;
    }
}
