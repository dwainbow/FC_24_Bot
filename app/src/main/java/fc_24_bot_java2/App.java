package fc_24_bot_java2;

public class App {
    public static void main(String[] args) {
        // var bot = new Bot();
        // bot.goToTransferMarket();
        // bot.buyPlayers("Calvin Verdonk", "8000");
        // bot.sellPlayers(500);
        var dataBaseDriver = new DatabaseDriver("fc24.db");
        try {
            dataBaseDriver.connect();
            var players = dataBaseDriver.getPlayersByName("De Paul");
            System.out.println(players);
        } catch (Exception e) {
            System.out.println("Failed to connect to database");
        }
    }
}
