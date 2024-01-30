package fc_24_bot_java2;
import org.jboss.aerogear.security.otp.Totp;

public class Authenticator {

    public static String getTOTPCode() {
        try {
            Config config = new Config();
            var code = config.getToken();
            var totp = new Totp(code);
            var twoFactorCode = totp.now();
            return twoFactorCode;
        } catch (Exception e) {
            System.out.println("Authenticator code is invalid. Re-enter your user information");
            new Config().resetConfig();
            System.out.println("Please Restart the program");
            System.exit(0);
            return null;
        }
        
    }
    
}
