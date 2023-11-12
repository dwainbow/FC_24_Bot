package fc_24_bot_java2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PopulateDatabase {
    public static void main(String[] args) throws SQLException {
        var populateDatabase = new PopulateDatabase("fc24.db");
        populateDatabase.connect();
        populateDatabase.createTables();
    }
    private final String sqliteFilename;
    private Connection connection;

    public PopulateDatabase(String sqliteFilename) {
        this.sqliteFilename = sqliteFilename;
    }

    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            throw new IllegalStateException("The connection is already opened");
        }
        connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteFilename);

        connection.createStatement().execute("PRAGMA foreign_keys = ON");
        connection.setAutoCommit(false);
    }
    
    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
    public void disconnect() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException {
        var statement = connection.createStatement();
        var playersSQL = "CREATE TABLE IF NOT EXISTS PLAYERS (" + 
    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
    "Name TEXT NOT NULL, " + 
    "Surname TEXT NOT NULL, " + 
    "Version TEXT NOT NULL, " + 
    "Position TEXT NOT NULL, " + 
    "Club TEXT NOT NULL, " + 
    "OtherPositions TEXT NOT NULL, " +  
    "PRICE REAL NOT NULL)";

        statement.executeUpdate(playersSQL);
        connection.commit();
    }
}
