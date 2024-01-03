package fc_24_bot_java2;

public class Config {
    private static DatabaseDriver instance;

    public static DatabaseDriver getInstance(){
        if (instance == null){
            instance = new DatabaseDriver();
        }
        return instance;
    }
}
