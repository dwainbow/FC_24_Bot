package fc_24_bot_java2;

import java.util.Scanner;

/**
 * The main class of the FUT Trading Bot application.
 * This class contains the entry point of the application and handles the user interaction.
 */
public class Main {
    public static void main(String[] args) {
        var config = new Config();
        var dataBaseDriver = Config.getInstance();        
        try {
            dataBaseDriver.connect();
            Player playerBuy;
            User user = new User(config);

            System.out.println("Welcome to the FUT Trading Bot!");
            
            var bot = new Bot(user);
            bot.goToTransferMarket();

            System.out.println("Enter the player name who you want to snipe: ");

            var input = new Scanner(System.in);
            var playerName = input.nextLine().strip();
            var players = dataBaseDriver.getPlayersByName(playerName);

            if (players.size() >1 ) {
                System.out.println("There is more than one, which one do you want to trade? ");
                for (int i = 0; i < players.size(); i++) {System.out.println(i + ") " + players.get(i).toString());}
                while(true)
                {
                    System.out.print("Enter the choice number: ");
                    var choice = input.nextInt();
                    if (choice >= 0 && choice <= players.size()-1) {
                        playerBuy = players.get(choice);
                        break;
                    }
                    else {
                        System.out.println("Invalid choice, try again");
                    }
                    
                }
                
            }
            else {
                playerBuy = players.get(0);
            }

            System.out.println("The recommended price is " +playerBuy.getPrice()+ " , Would you like to set a max buy price? (y/n)");
            var choice = input.next().strip();
            if(choice.equals("y"))
            {
                System.out.print("Enter the max buy price: ");
                var maxPrice = input.nextInt();
                bot.buyPlayers(playerBuy, maxPrice);
            }
            else
            {
                bot.buyPlayers(playerBuy);

            }
            System.out.println("Buying: " + playerBuy.toString());

            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unknown error occured. Restart application and try again");
            return;
        }
    }
}
