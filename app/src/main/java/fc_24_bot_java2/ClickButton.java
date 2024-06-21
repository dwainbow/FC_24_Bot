package fc_24_bot_java2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * The ClickButton class represents a button that can be clicked on a web page.
 */
public class ClickButton {
    private String buttonName;
    private WebDriver driver;

    /**
     * Constructs a ClickButton object with the specified button name and WebDriver.
     *
     * @param buttonName the name of the button
     * @param driver the WebDriver used to interact with the web page
     */
    public ClickButton(String buttonName, WebDriver driver) {
        this.buttonName = buttonName;
        this.driver = driver;
    }

    /**
     * Clicks the button with the specified XPath.
     *
     * @param xpath the XPath of the button to be clicked
     * @return true if the button is successfully clicked, false otherwise
     */
    public boolean click(String xpath) {
        try {
            int seconds = 3;
            var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            button.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
