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
        var failureCount = 0;
        var successCount = 0;
        int threshold;
        if(player.getPrice()>2000)
        {
            threshold = 10;
        }
        else
        {
            threshold = 5;
        }
        csv.addRow("0", "0");
        csv.writeToFile();
        webapp.setFilters(player);
        webapp.setMaxBuyPrice(Double.toString(player.getPrice()*0.90));
        var playersAcquired= 0;
        var attempts= 0;
        var initCoins = webapp.getCoins();
        if(initCoins< player.getPrice()*0.90)
        {
            System.out.println("Not enough coins to buy player");
            return;
        }
        System.out.println("Searching...");
        while(playersAcquired<5 && initCoins>=player.getPrice()*0.90)
        {
            if(attempts > threshold)
            {
                webapp.resetMinBuyPrice();
                attempts= 0 ;
            }
            webapp.incrementMinBuyPrice();
            webapp.search();
            
            if (webapp.buyPlayer())
            {
                
                if(webapp.getCoins()<initCoins)
                {
                    successCount++;
                    System.out.println("Successfully bought player");
                    csv.updateRow(0, Integer.toString(successCount), Integer.toString(failureCount));
                }   
                else
                {
                    System.out.println("Unsuccesfully bought player");
                    failureCount++;
                    csv.updateRow(0, Integer.toString(successCount), Integer.toString(failureCount));
                    
                }

                if (initCoins < player.getPrice() * 0.90) {
                    System.out.println("Not enough coins to continue Trading");
                    return;
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
