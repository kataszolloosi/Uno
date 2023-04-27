import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        deck.createCards();
        System.out.println(deck);

        Player kata = new Player("Kata", 1);
        Player ksenija = new Player("Ksenija", 2);
        Player nora = new Player("Nora", 3);
        Player marlies = new Player("Marlies", 4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(kata);
        players.add(ksenija);
        players.add(nora);
        players.add(marlies);
        System.out.println(players);

        Game game = new Game();

        game.shareCards();
        System.out.println(game);


    }
}
