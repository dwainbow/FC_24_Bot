package fc_24_bot_java2;

import java.util.*;
import java.io.*;

public class Config {
    private static DatabaseDriver instance;
    private Properties configProps;
    private String configFilePath = "user.config";

    public static DatabaseDriver getInstance(){
        if (instance == null){
            instance = new DatabaseDriver();
        }
        return instance;
    }

    public Config() {
        configProps = new Properties();
        loadConfig();
    }



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

    private void saveConfig() {
        try (FileOutputStream fos = new FileOutputStream(configFilePath)) {
            configProps.store(fos, "User Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return configProps.getProperty("password");
    }

    public String getEmail() {
        return configProps.getProperty("email");
    }

    public String getToken() {
        return configProps.getProperty("token");
    }
}
