import java.util.ArrayList;

public class Game {

    private ArrayList<Player> playersInGame = new ArrayList<>();
    private CardDeck cardDeck = new CardDeck();   //original carddeck
    private CardDeck discardPile = new CardDeck();  //ablegestapel




    public void start() {
        shareCards();   //karten austeilen
        layStartCard();  //erste karte auf dem tisch
//        cardChoice();

    }

   public void cardChoice() {

        for (Player p : playersInGame) {
            System.out.println("Welche Karte möchten Sie ausspielen?");
            p.playerDropCard();
            discardPile.addToDiscardPile(p.playerDropCard());
        }
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
