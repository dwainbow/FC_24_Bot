package fc_24_bot_java2;



/**
 * Represents a user in the system.
 */
public class User {
    private String email;
    private String password;
    private String code;

    /**
     * Constructs a new User object with the provided configuration.
     *
     * @param config The configuration object containing user details.
     */
    public User(Config config) {
        this.email = config.getEmail();
        this.password = config.getPassword();
        this.code = Authenticator.getTOTPCode();
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the verification code of the user.
     *
     * @return The verification code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}


