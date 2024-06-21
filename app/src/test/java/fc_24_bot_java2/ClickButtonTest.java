package fc_24_bot_java2;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClickButtonTest {
    @Test
    void click_ShouldReturnTrue_WhenButtonIsClickable() {
        // Arrange
        String xpath = "//button[@id='myButton']";
        WebDriver driver = mock(WebDriver.class);
        WebDriverWait wait = mock(WebDriverWait.class);
        WebElement button = mock(WebElement.class);
        when(driver.findElement(By.xpath(xpath))).thenReturn(button);
        when(wait.until(ExpectedConditions.elementToBeClickable(button))).thenReturn(button);

        ClickButton clickButton = new ClickButton("myButton", driver);

        // Act
        boolean result = clickButton.click(xpath);

        // Assert
        assertTrue(result);
        verify(button, times(1)).click();
    }

    @Test
    void click_ShouldReturnFalse_WhenButtonIsNotClickable() {
        // Arrange
        String xpath = "//button[@id='myButton']";
        WebDriver driver = mock(WebDriver.class);
        WebDriverWait wait = mock(WebDriverWait.class);
        when(driver.findElement(By.xpath(xpath))).thenThrow(new RuntimeException());

        ClickButton clickButton = new ClickButton("myButton", driver);

        // Act
        boolean result = clickButton.click(xpath);

        // Assert
        assertFalse(result);
    }
}