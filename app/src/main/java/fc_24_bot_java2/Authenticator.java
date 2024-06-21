package fc_24_bot_java2;
import org.jboss.aerogear.security.otp.Totp;

/**
 * The Authenticator class provides methods for generating a Time-based One-Time Password (TOTP) code.
 */
public class Authenticator {

    /**
     * Generates a TOTP code using the configured token.
     * If an exception occurs during the process, the user is prompted to re-enter their information and the program is restarted.
     *
     * @return The generated TOTP code.
     */
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
