package fc_24_bot_java2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class ClickButton {
    private String buttonName;
    private WebDriver driver;

    public ClickButton(String buttonName, WebDriver driver) {
        this.buttonName = buttonName;
        this.driver = driver;
        
    }
    public boolean click(String xpath) {
        try {
            int seconds = 1;
            var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            button.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    

    


}
