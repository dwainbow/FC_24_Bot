package fc_24_bot_java2;

public class App {
    public static void main(String[] args) {
        var bot = new Bot();
        bot.goToTransferMarket();
        bot.buyPlayers("Marquinhos", "14000");
        // bot.sellPlayers(500);
    }
}
