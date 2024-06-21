package fc_24_bot_java2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testGetEmail() {
        Config config = new Config("test@example.com", "password");
        User user = new User(config);

        String expectedEmail = "test@example.com";
        String actualEmail = user.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testGetCode() {
        Config config = new Config("test@example.com", "password");
        User user = new User(config);

        String expectedCode = Authenticator.getTOTPCode();
        String actualCode = user.getCode();

        assertEquals(expectedCode, actualCode);
    }

    @Test
    void testGetPassword() {
        Config config = new Config("test@example.com", "password");
        User user = new User(config);

        String expectedPassword = "password";
        String actualPassword = user.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }
}