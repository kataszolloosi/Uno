public class Main {
    public static void main(String[] args) {
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
        System.out.println(gamePlayers);
        gamePlayers.cardChoice();
        System.out.println(gamePlayers);


    }
}
