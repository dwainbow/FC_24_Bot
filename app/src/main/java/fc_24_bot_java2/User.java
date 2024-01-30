package fc_24_bot_java2;



public class User {
private String email;
private String password;
private String code;

public User(Config config) {
    this.email = config.getEmail();
    this.password = config.getPassword();
    this.code = Authenticator.getTOTPCode();

}

public String getEmail() {
    return email;

}

public String getCode() {
    return code;

}

public String getPassword() {
    return password;

}
}


