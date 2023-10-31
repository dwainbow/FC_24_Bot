package fc_24_bot_java2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebApp {
    private final WebDriver driver;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public int getCoins() {
        String coins = driver.findElement(By.className("view-navbar-currency-coins")).getText().replace(",", "");
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
            Thread.sleep(40000);
            driver.findElement(By.className("ut-tab-bar-item icon-transfer")).click();
        } catch (Exception e) {
            // TODO: handle exception
        }
        driver.findElement(By.className("ut-tab-bar-item icon-transfer")).click();
        // var button = new ClickButton("Transfers", driver);
        // button.click("/html/body/main/section/nav/button[3]");
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
            Thread.sleep(500);
            driver.findElement(By.className("contents"));
            goBackToTransfer();
            Thread.sleep(500);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void reset(String playerName) {
        System.out.println("Error occurred, resetting");
        goToTransfers();
        goToTransferMarket();
        enterPlayerName(playerName);
    }

    public void incrementMinBuyPrice() {
        var button = new ClickButton("Increment Min Buy", driver);
        button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/button[2]");
    }

    public void resetMinBuyPrice() {
        String xpath = "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/input";
        driver.findElement(By.xpath(xpath)).clear();
    }

    public void search() {
        var button = new ClickButton("Search", driver);
        button.click("/html/body/main/section/section/div[2]/div/div[2]/div/div[2]/button[2]");
    }

    public boolean buyPlayer() {
        var buyPlayerButton = new ClickButton("Buy Player", driver);
        buyPlayerButton.click("/html/body/main/section/section/div[2]/div/div/section[2]/div/div/div[2]/div[2]/button[2]");
        var confimPurchase = new ClickButton("Confirm Purchase", driver);
        confimPurchase.click("/html/body/div[4]/section/div/div/button[1]/span[1]");
        goBackToTransfer();
        return true;
    }

    public void goBackToTransfer() {
        var button = new ClickButton("Go back to transfer market", driver);
        button.click("/html/body/main/section/section/div[1]/button[1]");
    }

}
