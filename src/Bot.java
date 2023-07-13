public class Bot extends Player {
    public Bot(String name, int playersNumber) {
        super(name, playersNumber);
    }

    public void botCardChoice() {
        Player currentPlayer = Game.currentPlayer();

        System.out.println("\nPlayer " + currentPlayer.getName() + " your turn");
        Game.penalty();
        if (Game.canPlayerDropACard()) {
            System.out.println("Your cards: " + "\n" + currentPlayer.showMyCards());
            System.out.println("Which card do you want to play?");
            //playerDropCard()-> Spiel choice mit Scanner
            Game.getDiscardPile().addToPile(currentPlayer.playerDropCard());
            System.out.println("Card on Table: " + Game.getDiscardPile().getTopCard(Game.getDiscardPile()));
        } else {
            System.out.println("Yout still don't have a card to play");
            System.out.println("\nCard on Table: " + Game.getDiscardPile().getTopCard(Game.getDiscardPile()) + "\n");
        }
        Game.checkNextTurn();
    }
}
