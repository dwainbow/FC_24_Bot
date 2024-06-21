package fc_24_bot_java2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The WebApp class represents a web application for interacting with a specific website.
 * It provides methods for navigating the website, setting filters, searching for players,
 * and buying players from the transfer market.
 */
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
            Thread.sleep(3000);
            driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/div[2]/div[2]")).click();
        
        } catch (Exception e) {
            System.out.println("Error going to transfer market");
            return;
        }
    }
    

    public void goToTransfers() {
        try {
            Thread.sleep(10000);
            var button = new ClickButton("Transfers", driver);
            button.click("/html/body/main/section/nav/button[3]");
        } catch (Exception e) {
            System.out.println("Error going to transfers");
            return;
        }
        
    }

    private void setMaxBuyPrice(String playerPrice) {
        try {
            String xpath = "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[6]/div[2]/input";
            driver.findElement(By.xpath(xpath)).sendKeys(String.valueOf(playerPrice));
        } catch (Exception e) {
            System.out.println("Error setting max buy price");
            return;
        }
        
    }

    public void enterPlayerName(String playerName) {
        driver.findElement(By.className("ut-text-input-control")).sendKeys(playerName);
        var button = new ClickButton("Enter Player Name", driver);
        button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[2]/ul/button/span[1]");
    }

    public void setFilters(Player player)
    {
        enterPlayerName(player.getName());
        setQuality(player);
        setRarity(player);
        setMaxBuyPrice(Double.toString(player.getPrice()*0.85));
    }

    public void setFilters(Player player, int maxPrice)
    {
        enterPlayerName(player.getName());
        setQuality(player);
        setRarity(player);
        setMaxBuyPrice(Double.toString(maxPrice));
    }
    private void setQuality(Player player)
    {
        var button = new ClickButton("Quality", driver);
        var version = player.getVersion();
        if (version.startsWith("Gold")){version = "Gold";}
        else if(version.startsWith("Silver")){version= "Silver";}
        else if(version.startsWith("Bronze")){version= "Bronze";}
        else{version = "Special";}
        button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div");
        var elements =driver.findElements(By.className("with-icon"));
        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getText().equals(version))
            {
                elements.get(i).click();
                return;
            }
        }

    }
    private void setRarity(Player player)
    {
     
        var version = player.getVersion();
        if (version.endsWith("Common")){version = "Common";}
        else if(version.endsWith("Rare")){version= "Rare";}
        else if(version.endsWith("Team of the Week")){version= "Team of the Week";}
        var button = new ClickButton("Rarity ", driver);
        button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[3]/div/div/span");
        var elements =driver.findElements(By.className("with-icon"));
        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getText().equals(version))
            {
                elements.get(i).click();
                return;
            }
        }

    }
   

    private void incrementMinBuyPrice() {
        try {
            Thread.sleep(500);
            var button = new ClickButton("Increment Min Buy", driver);
            button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/button[2]");
        } catch (Exception e) {
            System.out.println("Error incrementing min buy price");
        }
    }
    
    public void resetMinBuyPrice() {
        String xpath = "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/input";
        driver.findElement(By.xpath(xpath)).clear();
    }

    public void search() {
        incrementMinBuyPrice();
        driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div[2]/div/div[2]/button[2]")).click();
    }   

    public boolean buyPlayer() {
        try {
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/button[2]")).click();
            var elements = driver.findElements(By.className("btn-text"));
            elements.get(2).click();
            Thread.sleep(500);
            goBackToTransferMarket();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void goBackToTransferMarket() {
        try {
            driver.findElement(By.className("ut-navigation-button-control")).click();
            var button = new ClickButton("Go Back Transfer", driver);
            button.click("/html/body/main/section/section/div[1]/button[1]");
        } catch (Exception e) {
        }
    }

}
