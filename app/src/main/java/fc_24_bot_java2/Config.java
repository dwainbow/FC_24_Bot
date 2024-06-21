package fc_24_bot_java2;

import java.util.*;
import java.io.*;

/**
 * The Config class represents the configuration settings for the FUT Trading Bot.
 * It provides methods to load, save, and retrieve the configuration properties.
 */
public class Config {
    private static DatabaseDriver instance;
    private Properties configProps;
    private String configFilePath = "user.config";

    /**
     * Returns the singleton instance of the DatabaseDriver.
     * If the instance does not exist, it creates a new one.
     *
     * @return The DatabaseDriver instance.
     */
    public static DatabaseDriver getInstance(){
        if (instance == null){
            instance = new DatabaseDriver();
        }
        return instance;
    }

    /**
     * Constructs a new Config object and loads the configuration properties.
     */
    public Config() {
        configProps = new Properties();
        loadConfig();
    }

    /**
     * Loads the configuration properties from the config file.
     * If the config file does not exist, it prompts the user for information and creates a new config file.
     */
    private void loadConfig() {
        File configFile = new File(configFilePath);
        if (configFile.exists() && !configFile.isDirectory()) {
            // Load properties from existing config file
            try (FileInputStream fis = new FileInputStream(configFile)) {
                configProps.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Config file does not exist, ask user for information
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Welcome to the FUT Trading Bot! Please follow the instructions to set up your bot");
                
                System.out.print("Enter your EA email:");
                String email = scanner.nextLine();
                
                System.out.print("Enter your EA password:");
                String password = scanner.nextLine();

                System.out.print("Enter your Authenticator token:");
                String token = scanner.nextLine();

                // Set properties
                configProps.setProperty("email", email);
                configProps.setProperty("password", password);
                configProps.setProperty("token", token);


                // Save properties to the config file
                try (FileOutputStream fos = new FileOutputStream(configFile)) {
                    configProps.store(fos, "User Configuration");
                } catch (IOException e) {
                    System.out.println("Error saving configuration file.");
                }
            }
        }
        
    }

    /**
     * Resets the configuration by prompting the user for information and saving the new configuration.
     */
    public void resetConfig() {
        // Prompt user for information and reset configuration
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
                
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your authentication token: ");
            String token = scanner.nextLine();

            // Set properties
            configProps.setProperty("email", email);
            configProps.setProperty("password", password);
            configProps.setProperty("token", token);

            // Save properties to the config file
            saveConfig();
        }
    }

    /**
     * Saves the configuration properties to the config file.
     */
    private void saveConfig() {
        try (FileOutputStream fos = new FileOutputStream(configFilePath)) {
            configProps.store(fos, "User Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the password from the configuration properties.
     *
     * @return The password.
     */
    public String getPassword() {
        return configProps.getProperty("password");
    }

    /**
     * Returns the email from the configuration properties.
     *
     * @return The email.
     */
    public String getEmail() {
        return configProps.getProperty("email");
    }

    /**
     * Returns the authentication token from the configuration properties.
     *
     * @return The authentication token.
     */
    public String getToken() {
        return configProps.getProperty("token");
    }
}
