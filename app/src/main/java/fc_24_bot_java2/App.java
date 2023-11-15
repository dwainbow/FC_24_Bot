package fc_24_bot_java2;

public class App {
    public static void main(String[] args) {
        var dataBaseDriver = new DatabaseDriver("fc24.db");
        try {
            dataBaseDriver.connect();
            
            var players = dataBaseDriver.getPlayersByName("Ferland Mendy");
            
            var playerBuy = players.get(1);
            System.out.println(playerBuy);
            var bot = new Bot();
            bot.goToTransferMarket();
            bot.buyPlayers(playerBuy);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
