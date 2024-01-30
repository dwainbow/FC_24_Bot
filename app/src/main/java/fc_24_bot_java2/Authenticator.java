package fc_24_bot_java2;
import org.jboss.aerogear.security.otp.Totp;

public class Authenticator {

    public static String getTOTPCode() {
        Config config = new Config();
        //jsdt6ttyp5xbmicp
        var code = config.getToken();
        var totp = new Totp(code);
        var twoFactorCode = totp.now();
        return twoFactorCode;
    }
    
}
