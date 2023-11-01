public class Bot
{
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
        var playersAcquired= 0;
        var attempts= 0;
        var initCoins = webapp.getCoins()
        while(true)
        {
            buyPlayers()
            sellPlayers(playerPrice)
        }
    } 
    private boolean buyPlayerProcess() {
        try {
            var buyPlayerButton = new ClickButton("Buy Player", driver);
            buyPlayerButton.click("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/button[2]");
            System.out.println("Bought Player");
            // var confimPurchase = new ClickButton("Confirm Purchase", driver);
            // confimPurchase.click("/html/body/div[4]/section/div/div/button[1]/span[1]");
            Thread.sleep(3000);
            goBackToTransferMarket();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void buyPlayers()
    {
        var playersAcquired= 0;
        var attempts= 0;
        var initCoins = webapp.getCoins()
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
            if (buyPlayerProcess())
            {
                if(webapp.getCoins()<initCoins)
                {
                    System.out.println("Succesfully bought player");
                    initCoins = webapp.getCoins();
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
        goHome();
        goToUnnasignedItems();
        while(!noResult())
        {
            pressListTransferMarket();
            setListingPrice(amount);
            listOnTransfer();
        }
    }

}