package fc_24_bot_java2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * The LoginPage class represents a page for logging into EA.
 * It provides methods for setting up the WebDriver, logging in, authenticating, and logging into the WebApp.
 */
public class LoginPage {
    private String email;
    private String password;
    private String code;
    private WebDriver driver;

    public LoginPage(User user) {
        System.out.println("Logging into EA...");  
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.code = user.getCode();
        setDriver();
        login();
        System.out.println("Authenticating...");
        authenticate();
        loginWebApp();
        System.out.println("Logging into WebApp...");
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


    private void login()
    {
        try {
            var username = driver.findElement(By.id("email"));
            username.sendKeys(email);

            var password = driver.findElement(By.id("password"));
            password.sendKeys(this.password);

            var loginButton = driver.findElement(By.id("logInBtn"));
            loginButton.click();
            
        } catch (Exception e) {

        }
        
    }
   
    private void authenticate()
    {
        try {
            var authenticator = driver.findElement(By.id("APPLabel"));
            authenticator.click();
            var sendCode = driver.findElement(By.id("btnSendCode"));
            sendCode.click();
            var twoFactorCode = driver.findElement(By.id("twoFactorCode"));
            twoFactorCode.sendKeys(code);
            var verifyButton = driver.findElement(By.id("btnSubmit"));
            verifyButton.click();
            
        } catch (Exception e) {
            System.out.println("Error authenticating. User information may be incorrect");
            new Config().resetConfig();
            System.out.println("Please restart the program");
            System.exit(0);
        }

    }
    private void loginWebApp()
    {
        try {
            Thread.sleep(10000);
            var button = new ClickButton("loginWebApp", driver);
            button.click("/html/body/main/div/div/div/button[1]");
            Thread.sleep(10000);
        } catch (Exception e) {
            
        }
    }


}
