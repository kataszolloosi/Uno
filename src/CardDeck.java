import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<Card> discardPile = new ArrayList<>();

    public CardDeck() {   //default konstruktor
//        cards = new ArrayList<>();
        createCards();    //creating carddeck
    }

    @Override
    public String toString() {
        return "CardDeck" + cards;
    }

    //carddeck erstellen mit alle aktionskarten
    public void createCards() {
        //blaue karte erstellen
        for (int i = 0; i < 2; i++) {  //stückzahl
            cards.add(new Card("Reverse", "Blue", 20));
            cards.add(new Card("+2", "Blue", 20));
            cards.add(new Card("Stop", "Blue", 20));
            for (int j = 1; j < 10; j++) {  //zahlen
                cards.add(new Card(Integer.toString(j), "Blue", j));
            }
        }

        for (int i = 0; i < 2; i++) {
            //grüne karte erstellen
            cards.add(new Card("Reverse", "Green", 20));
            cards.add(new Card("+2", "Green", 20));
            cards.add(new Card("Stop", "Green", 20));
            for (int j = 1; j < 10; j++) {
                cards.add(new Card(Integer.toString(j), "Green", j));
            }
        }
        for (int i = 0; i < 2; i++) {
            //rote karte erstellen
            cards.add(new Card("Reverse", "Red", 20));
            cards.add(new Card("+2", "Red", 20));
            cards.add(new Card("Stop", "Red", 20));
            for (int j = 1; j < 10; j++) {
                cards.add(new Card(Integer.toString(j), "Red", j));
            }
        }

        for (int i = 0; i < 2; i++) {
            //gelbe karte erstellen
            cards.add(new Card("Reverse", "Yellow", 20));
            cards.add(new Card("+2", "Yellow", 20));
            cards.add(new Card("Stop", "Yellow", 20));
            for (int j = 1; j < 10; j++) {
                cards.add(new Card(Integer.toString(j), "Yellow" , j));
            }
        }
        for (int b = 0; b < 1; b++) {
            //null karte erstellen
            cards.add(new Card("0", "Blue", 0));
            cards.add(new Card("0", "Green", 0));
            cards.add(new Card("0", "Red", 0));
            cards.add(new Card("0", "Yellow", 0));
        }

        for (int b = 0; b < 4; b++) {
            //color change karten erstellen
            cards.add(new Card("+4", "Black", 50));
            cards.add(new Card("ColorChange", "Black", 50));
        }
        Collections.shuffle(cards);
    }


    public static Card drawCard() {
//        if (cards.isEmpty()) {
//            reshuffleDiscardPile(); // Wenn das Deck leer ist, mische den Ablagestapel zurück ins Deck
//        }
        return cards.remove(cards.size() - 1); // Entferne und gib die oberste Karte des Decks zurück
    }

    public static void reshuffleDiscardPile() {
        if (discardPile.size() > 1) {
            Card topCard = discardPile.remove(discardPile.size() - 1); // Entferne die oberste Karte vom Ablagestapel
            Collections.shuffle(discardPile); // Mische den Ablagestapel
            cards.addAll(discardPile); // Füge die gemischten Karten zum Deck hinzu
            discardPile.clear(); // Leere den Ablagestapel
            discardPile.add(topCard); // Lege die zuvor entfernte Karte als erste Karte auf den neuen Ablagestapel
        }
    }

    public void addToDiscardPile(Card playerDropCard) {
        //gespielte karte zum neue stapel
        cards.add(playerDropCard);
        //discardPile.add(playerDropCard); // Füge die übergebene Karte dem Ablagestapel hinzu
    }

    public static Card getDropCard() {
          //die oberste Karte des Decks zu erhalten, ohne sie vom Deck zu entfernen
        //eine karte ausspielen
        return cards.get(cards.size()-1);
    }


}
