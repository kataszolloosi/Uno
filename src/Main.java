import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //4menschliche spieler
        Player kata = new Player("Kata", 1);
        Player ksenija = new Player("Ksenija", 2);
        Player nora = new Player("Nora", 3);
        Player marlies = new Player("Marlies", 4);

        //spieler zu game
        Game gamePlayers = new Game();
        gamePlayers.addPlayerToPlayerList(kata);
        gamePlayers.addPlayerToPlayerList(ksenija);
        gamePlayers.addPlayerToPlayerList(nora);
        gamePlayers.addPlayerToPlayerList(marlies);
        gamePlayers.start();
        System.out.println(gamePlayers);

    }
}
