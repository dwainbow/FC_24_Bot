package fc_24_bot_java2;

public class Bot {
    private LoginPage login;
    private WebApp webapp;
    private CSV csv = CSV.getInstance();
    private int successCount;
    private int failureCount;

    public Bot(String email,String password)
    {
        this.login = new LoginPage(email, password);
        this.webapp = new WebApp(login.getDriver());
        goToTransferMarket();
    }
    private void goToTransferMarket()
    {
        webapp.goToTransfers();
        webapp.goToTransferMarket();
    }
    public void trade(Player player)
    {
        while(true)
        {
            buyPlayers(player);
            // sellPlayers(player);
        }
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
    private void buyPlayers(Player player)
    {
        webapp.setFilters(player);
        var initCoins = webapp.getCoins();
        var attempts= 0;
        var playersAcquired= 0;
        var threshold = setThreshold(player);
        
        System.out.println("Searching...");

        while(playersAcquired<5 && initCoins>=player.getPrice()*0.85)
        {
            if(initCoins< player.getPrice()*0.85)
            {
                System.out.println("Cannot Trade, not enough coins");
                return;
            }
            if(attempts > threshold)
            {
                webapp.resetMinBuyPrice();
                attempts= 0 ;
            }
            // var start =System.currentTimeMillis();
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
            // var end = System.currentTimeMillis();
            // System.out.println((double)end-start);
        }

        
    }
    // public void sellPlayers(Player player)
    // {
    //     // webapp.goHome();
    //     webapp.goToUnnasignedItems();
    //     while(!webapp.noResult())
    //     {
    //         webapp.pressListTransferMarket();
    //         webapp.setListingPrice((int) player.getPrice());
    //         // webapp.listOnTransfer();
    //     }
    // }
}
