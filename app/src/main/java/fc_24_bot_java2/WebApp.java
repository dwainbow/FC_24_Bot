package fc_24_bot_java2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class WebApp {
    private final WebDriver driver;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public int getCoins() {
        String coins = driver.findElement(By.className("view-navbar-currency-coins"))
        .getText()
        .replace(",", "");
        return Integer.parseInt(coins);
    }

    public void goToTransferMarket() {
        try {
            Thread.sleep(5000);
            driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/div[2]/div[2]")).click();
            System.out.println("Going to transfer Market...");
        
        } catch (Exception e) {
            System.out.println("Failed to go to transfer Market");
        }
    }
    

    public void goToTransfers() {
        try {
            Thread.sleep(20000);
            var button = new ClickButton("Transfers", driver);
            button.click("/html/body/main/section/nav/button[3]");
        } catch (Exception e) {
            
        }
        
    }

    public void setMaxBuyPrice(String playerPrice) {
        try {
            String xpath = "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[6]/div[2]/input";
            driver.findElement(By.xpath(xpath)).sendKeys(String.valueOf(playerPrice));
        } catch (Exception e) {
            System.out.println("Failed to set max buy price");
            return;
        }
        
    }

    public void enterPlayerName(String playerName) {
        driver.findElement(By.className("ut-text-input-control")).sendKeys(playerName);
        var button = new ClickButton("Enter Player Name", driver);
        button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[2]/ul/button/span[1]");
    }

    public boolean noResult() {
        try {
            Thread.sleep(1000);
            driver.findElement(By.className("ut-no-results-view"));
            goBackToTransferMarket();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void goHome()
    {
        var button = new ClickButton("Home", driver);
        button.click("/html/body/main/section/nav/button[1]");
    }
    public void goToUnnasignedItems()
    {
        var button = new ClickButton("UnassignedItems", driver);
        button.click("/html/body/main/section/section/div[2]/div/div/div[1]/div[1]");
    }
    public void pressListTransferMarket()
    {
        var button = new ClickButton("List on Transfer Button", driver);
        button.click("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]");

    }
    public void setListingPrice(int maxPrice)
    {  
        maxPrice = 500;
        driver
        .findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/div[2]/div[3]/div[2]/input"))
        .sendKeys(Keys.DELETE);
        // driver
        // .findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/div[2]/div[3]/div[2]/input"))
        // .sendKeys(String.valueOf(maxPrice));

        driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/input"))
        .clear();
        // driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/input"))
        // .sendKeys("1000");
    }
    public void listOnTransfer()
    {
        var button = new ClickButton("List on Transfer", driver);
        button.click("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/div[2]/button");
    }
    
    public void reset(String playerName) {
        System.out.println("Error occurred, resetting");
        goToTransfers();
        goToTransferMarket();
        enterPlayerName(playerName);
    }

    public void incrementMinBuyPrice() {
        try {
            Thread.sleep(500);
            var button = new ClickButton("Increment Min Buy", driver);
            button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/button[2]");
        } catch (Exception e) {
            
        }
        
    }
    
    public void resetMinBuyPrice() {
        String xpath = "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/input";
        driver.findElement(By.xpath(xpath)).clear();
    }

    public void search() {
        driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div[2]/div/div[2]/button[2]")).click();
    }
    public boolean buyPlayer() {
        try {
            var buyPlayerButton = new ClickButton("Buy Player", driver);
            buyPlayerButton.click("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/button[2]");
            // var cancelBuyButton = new ClickButton("Cancel Buy", driver);
            // cancelBuyButton.click("/html/body/div[4]/section/div/div/button[2]");
            var confimPurchase = new ClickButton("Confirm Purchase", driver);
            confimPurchase.click("/html/body/div[4]/section/div/div/button[1]/span[1]");
            Thread.sleep(2000);
            goBackToTransferMarket();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void goBackToTransferMarket() {
        try {
            driver.findElement(By.className("ut-navigation-button-control")).click();
        } catch (Exception e) {
            System.out.println("Failed to go back to transfer market");
        }
    }

}
