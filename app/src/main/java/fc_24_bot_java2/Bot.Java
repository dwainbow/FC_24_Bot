package fc_24_bot_java2;

public class Bot {
    private LoginPage login;
    private WebApp webapp;

    public Bot()
    {
        login = new LoginPage("overthedwainbow@gmail.com", "Daniels2002");
        login.login();
        login.authenticate();
        login.loginWebApp();
        this.webapp = new WebApp(login.getDriver());
    }
    public void goToTransferMarket()
    {
        webapp.goToTransfers();
        webapp.goToTransferMarket();
    }
    public void Trade(Player player)
    {
        while(true)
        {
            buyPlayers(player);
            // sellPlayers(player);
        }
    } 
    public void buyPlayers(Player player)
    {
        var csv= new CSV();
        var errorCount =0;
        var failureCount = 0;
        var successCount = 0;
        csv.addRow(successCount,failureCount,errorCount);
        csv.writeToFile();
        
        webapp.enterPlayerName(player.getName());
        webapp.setMaxBuyPrice(Double.toString(player.getPrice()*0.85));
        var playersAcquired= 0;
        var attempts= 0;
        var initCoins = webapp.getCoins();
        if(initCoins< player.getPrice()*0.85)
        {
            System.out.println("Not enough coins to buy player");
            return;
        }
        System.out.println("Searching...");
        
        while(playersAcquired<5 && initCoins>=player.getPrice()*0.85)
        {
            if(attempts > 5)
            {
                webapp.resetMinBuyPrice();
                attempts= 0 ;
            }
            webapp.incrementMinBuyPrice();
            webapp.search();
            if (webapp.buyPlayer())
            {
                if(webapp.getCoins()<initCoins)
                successCount++;
                csv.updateRow(0, successCount, failureCount, errorCount);
                if (initCoins < player.getPrice() * 0.85) {
                    System.out.println("Not enough coins to continue Trading");
                    return;
                }
                else
                {
                    System.out.println("Unsuccesfully bought player");
                    failureCount++;
                    csv.updateRow(1, successCount, failureCount, errorCount);
                    
                }
                
            }
            else
            {
                webapp.goBackToTransferMarket();
            }
            attempts++;
        }

        
    }
    public void sellPlayers(Player player)
    {
        // webapp.goHome();
        webapp.goToUnnasignedItems();
        while(!webapp.noResult())
        {
            webapp.pressListTransferMarket();
            webapp.setListingPrice((int) player.getPrice());
            // webapp.listOnTransfer();
        }
    }
}
