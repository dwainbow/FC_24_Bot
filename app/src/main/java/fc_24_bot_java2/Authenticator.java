package fc_24_bot_java2;
import org.jboss.aerogear.security.otp.Totp;

public class Authenticator {

    public Authenticator() {
    }
    public static String getTOTPCode() {
        var code = "jsdt6ttyp5xbmicp";
        var totp = new Totp(code);
        var twoFactorCode = totp.now();
        return twoFactorCode;
    }
    
}