import java.util.Random;

public class Bot extends Player {
    public Bot(String name, int playersNumber) {
        super(name, playersNumber);
    }

    @Override
    public void giveCard(Card card) {
        //karte zu hand - heben
        getCardsInHand().add(card);
    }
    @Override
    public Card playerDropCard() {

        //menjen vegeig az osszes kartyan index szammal, hogy le tudja e rakni, ha nem, akkor huz egyet
       //for () //ha a forban error(try) jon vissza, ellenorzi a kovetkezo lapjat
           //ha jo a lap, vissza kell adnia es ne menjen tovabb
        return null;
    }
}
