package fc_24_bot_java2;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;



public class LoginPage {
    private String email;
    private String password;
    private WebDriver driver;

    public LoginPage(String email, String password) {
        this.email = email;
        this.password = password;
        setDriver();
    }

    private void setDriver() {
        FirefoxOptions options = new FirefoxOptions();
        
        // options.addArguments("--headless");
        // options.addArguments("--disable-gpu");
        this.driver= new FirefoxDriver(options);

        this.driver.get("https://signin.ea.com/p/juno/login?execution=e1540455597s1&initref=https%3A%2F%2Faccounts.ea.com%3A443%2Fconnect%2Fauth%3Fhide_create%3Dtrue%26display%3Dweb2%252Flogin%26scope%3Dbasic.identity%2Boffline%2Bsignin%2Bbasic.entitlement%2Bbasic.persona%26release_type%3Dprod%26response_type%3Dtoken%26redirect_uri%3Dhttps%253A%252F%252Fwww.ea.com%252Fea-sports-fc%252Fultimate-team%252Fweb-app%252Fauth.html%26accessToken%3D%26locale%3Den_US%26prompt%3Dlogin%26client_id%3DFC24_JS_WEB_APP");

    }
    public WebDriver getDriver() {
        return this.driver;
    }

    public void login()
    {
        try {
            System.out.println("Logging in...");
            var username = driver.findElement(By.id("email"));
            username.sendKeys(email);

            var password = driver.findElement(By.id("password"));
            password.sendKeys(this.password);

            var loginButton = driver.findElement(By.id("logInBtn"));
            loginButton.click();
            return ;
        } catch (Exception e) {
            System.out.println("Error logging in");
            return ;
        }
        
    }
    public void authenticate()
    {
        System.out.println("Authenticating...");
        var secondEmail = driver.findElement(By.id("SECOND_EMAILLabel"));
        secondEmail.click();

        var sendCode = driver.findElement(By.id("btnSendCode"));
        sendCode.click();

        var twoFactorCode = driver.findElement(By.id("twoFactorCode"));
        System.out.print("Enter 2FA code: ");
        Scanner code = new Scanner(System.in);
        twoFactorCode.sendKeys(code.nextLine());

        var verifyButton = driver.findElement(By.id("btnSubmit"));
        verifyButton.click();
        while(!validCode())
        {
            System.out.println("Enter 2FA code: ");
            code = new Scanner(System.in);
            twoFactorCode.sendKeys(code.nextLine());
            verifyButton.click();
        }
        System.out.println("Successfully authenticated");

    }
    public void loginWebApp()
    {
        System.out.println("Logging in to web app...");
        var button = new ClickButton("loginWebApp", driver);
        button.click("/html/body/main/div/div/div/button[1]");
        System.out.println("Successfully logged in to web app");

    }

    public boolean validCode()
    {
        try
        {
           var invalidCode = driver.findElement(By.xpath("/html/body/div[1]/form/div/section/div[3]/p"));
           return false;
        }
        catch(Exception  e)
        {
            System.out.println("Code is valid");
            return true;
        }
    }
        
        


}
