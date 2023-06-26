import java.util.Random;

public class Bot extends Player {
    public Bot(String name, int playersNumber) {
        super(name, playersNumber);
    }


    @Override
    public void addCardToHand(Card card) {
        super.addCardToHand(card);
    }

    @Override
    public Card playerDropCard() {
        Random random = new Random();
        int choice = random.nextInt(getCardsInHand().size());
        Card selectedCard = getCardsInHand().get(choice);
        if (Game.cardValidation(selectedCard)) {
            getCardsInHand().remove(choice);
            return selectedCard;
        }
        return null;
    }
}
