package fc_24_bot_java2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigTest {
    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
    }

    @Test
    void testLoadConfig_ExistingFile() {
        // Create a temporary config file
        File configFile = new File("temp.config");
        configFile.deleteOnExit();

        // Set up properties
        Properties properties = new Properties();
        properties.setProperty("email", "test@example.com");
        properties.setProperty("password", "password123");
        properties.setProperty("token", "123456");

        // Save properties to the config file
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(baos)) {
            properties.store(ps, "User Configuration");
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            config.setConfigFilePath(configFile.getAbsolutePath());

            // Load config from the existing file
            config.loadConfig();

            // Verify loaded properties
            assertEquals("test@example.com", config.getEmail());
            assertEquals("password123", config.getPassword());
            assertEquals("123456", config.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoadConfig_NonExistingFile() {
        // Create a temporary config file
        File configFile = new File("temp.config");
        configFile.delete();

        // Set up user input
        String userInput = "test@example.com\npassword123\n123456\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        // Set the config file path
        config.setConfigFilePath(configFile.getAbsolutePath());

        // Load config from the non-existing file
        config.loadConfig();

        // Verify loaded properties
        assertEquals("test@example.com", config.getEmail());
        assertEquals("password123", config.getPassword());
        assertEquals("123456", config.getToken());
    }

    @Test
    void testResetConfig() {
        // Set up user input
        String userInput = "test@example.com\npassword123\n123456\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        // Reset config
        config.resetConfig();

        // Verify reset properties
        assertEquals("test@example.com", config.getEmail());
        assertEquals("password123", config.getPassword());
        assertEquals("123456", config.getToken());
    }
}