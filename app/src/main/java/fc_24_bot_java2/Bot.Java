package fc_24_bot_java2;

public class Bot {
    private LoginPage login;
    private WebApp webapp;

    public Bot()
    {
        this.login = new LoginPage("overthedwainbow@gmail.com", "Daniels2002");
        this.webapp = new WebApp(login.getDriver());
    }
    public void goToTransferMarket()
    {
        webapp.goToTransfers();
        webapp.goToTransferMarket();
    }
    public void Trade(int playerPrice)
    {
        while(true)
        {
            buyPlayers();
            sellPlayers(playerPrice);
        }
    } 
    private void buyPlayers()
    {
        var playersAcquired= 0;
        var attempts= 0;
        var initCoins = webapp.getCoins();
        while(playersAcquired<5)
        {
            if(attempts > 15)
            {
                webapp.resetMinBuyPrice();
                attempts= 0 ;
            }
            webapp.incrementMinBuyPrice();
            webapp.search();
            if(webapp.noResult())
            {
                attempts++;
                continue;
            }
            if (webapp.buyPlayer())
            {
                if(webapp.getCoins()<initCoins)
                {
                    System.out.println("Succesfully bought player");
                    initCoins = webapp.getCoins();
                    playersAcquired++;
                }
                else
                {
                    System.out.println("Failed to buy player");
                }
                attempts++;
            }

        }
    }
    public void sellPlayers(int amount)
    {
        webapp.goHome();
        webapp.goToUnnasignedItems();
        while(!webapp.noResult())
        {
            webapp.pressListTransferMarket();
            webapp.setListingPrice(amount);
            webapp.listOnTransfer();
        }
    }
}
