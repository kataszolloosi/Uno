import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private final ArrayList<Card> cards = new ArrayList<>();

    public CardDeck() {   //default konstruktor
        createCards();    //creating carddeck
    }

    @Override
    public String toString() {
        return "CardDeck" + cards;
    }

    //carddeck erstellen mit alle aktionskarten
    public void createCards() {
        for (int i = 0; i < 2; i++) {  //stückzahl
            cards.add(new Card("Reverse", "Blue"));
            cards.add(new Card("+2", "Blue"));
            cards.add(new Card("Stop", "Blue"));
            for (int j = 1; j < 10; j++) {  //zahlen
                cards.add(new Card(Integer.toString(j), "Blue"));
            }
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Card("Reverse", "Green"));
            cards.add(new Card("+2", "Green"));
            cards.add(new Card("Stop", "Green"));
            for (int j = 1; j < 10; j++) {
                cards.add(new Card(Integer.toString(j), "Green"));
            }
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Card("Reverse", "Red"));
            cards.add(new Card("+2", "Red"));
            cards.add(new Card("Stop", "Red"));
            for (int j = 1; j < 10; j++) {
                cards.add(new Card(Integer.toString(j), "Red"));
            }
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Card("Reverse", "Yellow"));
            cards.add(new Card("+2", "Yellow"));
            cards.add(new Card("Stop", "Yellow"));
            for (int j = 1; j < 10; j++) {
                cards.add(new Card(Integer.toString(j), "Yellow"));
            }
        }
        for (int b = 0; b < 1; b++) {
            cards.add(new Card("0", "Blue"));
            cards.add(new Card("0", "Green"));
            cards.add(new Card("0", "Red"));
            cards.add(new Card("0", "Yellow"));
        }

        for (int b = 0; b < 4; b++) {
            cards.add(new Card("+4", "Black"));
            cards.add(new Card("Color Change", "Black"));
        }
        Collections.shuffle(cards);
    }


    public Card drawCard() {
        //methode karten aufheben
        return cards.remove(cards.size()-1);
    }

    public void addToCards(Card playerDropCard) {
        //gespielte karte zum neue stapel
        cards.add(playerDropCard);
    }

    public Card getDropCard() {
        //eine karte ausspielen
        return cards.get(cards.size()-1);
    }


}
