package fc_24_bot_java2;



public class User {
private String email;
private String password;
private String code;

public User(String email, String password) {
    this.email = email;
    this.password = password;
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


