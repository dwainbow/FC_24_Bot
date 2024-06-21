package fc_24_bot_java2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WebAppTest {
    private WebDriver mockDriver;
    private WebApp webApp;

    @BeforeEach
    void setUp() {
        mockDriver = mock(WebDriver.class);
        webApp = new WebApp(mockDriver);
    }

    @Test
    void testGetCoins() {
        WebElement mockElement = mock(WebElement.class);
        when(mockDriver.findElement(By.className("view-navbar-currency-coins"))).thenReturn(mockElement);
        when(mockElement.getText()).thenReturn("10,000");

        int coins = webApp.getCoins();

        assertEquals(10000, coins);
        verify(mockDriver).findElement(By.className("view-navbar-currency-coins"));
        verify(mockElement).getText();
    }

    @Test
    void testGoToTransferMarket() {
        WebElement mockElement = mock(WebElement.class);
        when(mockDriver.findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/div[2]/div[2]"))).thenReturn(mockElement);

        webApp.goToTransferMarket();

        verify(mockDriver).findElement(By.xpath("/html/body/main/section/section/div[2]/div/div/div[2]/div[2]"));
        verify(mockElement).click();
    }

    // Add more test cases for other methods in the WebApp class
}