package fc_24_bot_java2;

/**
 * The Bot class represents a bot that performs actions in a web application.
 * It interacts with the LoginPage and WebApp classes to perform actions such as logging in, navigating to the transfer market, and buying players.
 */
public class Bot {
    private LoginPage login;
    private WebApp webapp;
    private CSV csv = CSV.getInstance();
    private int successCount;
    private int failureCount;

    public Bot(User user)
    {
        this.login = new LoginPage(user);
        this.webapp = new WebApp(login.getDriver());
    }
    public void goToTransferMarket()
    {
        webapp.goToTransfers();
        webapp.goToTransferMarket();
    }
    private int setThreshold(Player player)
    {
        int threshold;
        if(player.getPrice()>5000)
        {
            threshold = 15;
        }
        else
        {
            threshold=10;
        }
        return threshold;
    }

    private void incrementSuccess()
    {
        successCount++;
        csv.updateRow(csv.getNumRows()-1, Integer.toString(successCount), Integer.toString(failureCount));
        System.out.println("Successfully bought player");
    }

    private void incremenFailure()
    {
        failureCount++;
        csv.updateRow(csv.getNumRows()-1, Integer.toString(successCount), Integer.toString(failureCount));
        System.out.println("Unsuccesfully bought player");
    }
    public void buyPlayers(Player player)
    {
        webapp.setFilters(player);
        var initCoins = webapp.getCoins();
        var attempts= 0;
        var playersAcquired= 0;
        var threshold = setThreshold(player);
        
        System.out.println("Searching...");

        while(playersAcquired<5 && initCoins>=player.getPrice()*0.95)
        {
            if(initCoins< player.getPrice()*0.95)
            {
                System.out.println("Cannot Trade, not enough coins");
                return;
            }
            if(attempts > threshold)
            {
                webapp.resetMinBuyPrice();
                attempts= 0 ;
            }
            webapp.search();
            if (webapp.buyPlayer())
            {
                if(webapp.getCoins()<initCoins)
                {
                    initCoins = webapp.getCoins();
                    incrementSuccess();
                }   
                else
                {
                    incremenFailure();
                }
            }
            webapp.goBackToTransferMarket(); 
            attempts++;
        }

        
    }

    public void buyPlayers(Player player, int maxPrice)
    {
        webapp.setFilters(player, maxPrice);
        var initCoins = webapp.getCoins();
        var attempts= 0;
        var playersAcquired= 0;
        var threshold = setThreshold(player);
        
        System.out.println("Searching...");
        while(playersAcquired<5 && initCoins>=player.getPrice()*0.95)
        {
            if(initCoins< player.getPrice()*0.95)
            {
                System.out.println("Cannot Trade, not enough coins");
                return;
            }
            if(attempts > threshold)
            {
                webapp.resetMinBuyPrice();
                attempts= 0 ;
            }
            webapp.search();
            if (webapp.buyPlayer())
            {                
                if(webapp.getCoins()<initCoins)
                {
                    initCoins = webapp.getCoins();
                    incrementSuccess();
                }   
                else
                {
                    incremenFailure();
                }
            }
            webapp.goBackToTransferMarket(); 
            attempts++;
        }

        
    }
    
}
