package fc_24_bot_java2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var dataBaseDriver = new DatabaseDriver("fc24.db");
        
        try {
            dataBaseDriver.connect();
            Player playerBuy;
            var players = dataBaseDriver.getPlayersByPrice(1000,500);
            if (players.size() >1 ) {
                System.out.println("There is more than one, which one do you want to trade?");
                for (int i = 0; i < players.size(); i++) {
                System.out.println(i + ") " + players.get(i).toString());
            }
            System.out.print("Enter the choice number: ");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            playerBuy = players.get(choice);
            } else {
                playerBuy = players.get(0);
            }
            var bot = new Bot("overthedwainbow@gmail.com","Daniels2002");
            bot.trade(playerBuy);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}