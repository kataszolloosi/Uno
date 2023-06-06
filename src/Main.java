import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Game gamePlayers = new Game();


        //4menschliche spieler
        Player kata = new Player("Kata", 1, gamePlayers);
        Player ksenija = new Player("Ksenija", 2, gamePlayers);
        Player nora = new Player("Nora", 3, gamePlayers);
        Player marlies = new Player("Marlies", 4, gamePlayers);

        //spieler zu game
        gamePlayers.addPlayerToPlayerList(kata);
        gamePlayers.addPlayerToPlayerList(ksenija);
        gamePlayers.addPlayerToPlayerList(nora);
        gamePlayers.addPlayerToPlayerList(marlies);
        gamePlayers.start();
        gamePlayers.cardChoice();



    }
}
