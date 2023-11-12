package fc_24_bot_java2;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;



public class DatabaseDriver {
    private Connection connection;
    private String sqliteFilename;

    public DatabaseDriver(String sqliteFilename) {
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
    public List<Player> getPlayersByName(String name)
    {
        try {
            var statement = connection.createStatement();
            List returnedPlayers = new ArrayList<Player>();   
            var result = statement.executeQuery(String.format("SELECT * FROM PLAYERS WHERE Name LIKE '%%%s%%'", name));
            while(result.next()) {
                var playerName = result.getString("Name");
                var version = result.getString("Version");
                var position = result.getString("Position");
                var club = result.getString("Club");
                var price = result.getDouble("Price");
                var nation = result.getString("Nation");

                var temp = new Player(playerName, version, position, nation, club, price);
                returnedPlayers.add(temp);
            }
            return returnedPlayers;
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Player> getPlayersByPosition(String position)
    {
        try {
            var statement= connection.createStatement();
            List returnedPlayers = new ArrayList<Player>();
            var result = statement.executeQuery(String.format("SELECT * FROM PLAYERS WHERE Position = '%s'", position));
            while(result.next()) {
                var playerName = result.getString("Name");
                var version = result.getString("Version");
                var position1 = result.getString("Position");
                var club = result.getString("Club");
                var price = result.getDouble("Price");
                var nation = result.getString("Nation");

                var temp = new Player(playerName, version, position1, nation, club, price);
                returnedPlayers.add(temp);
                
            }
            return returnedPlayers;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Player> getPlayersByNation(String nation)
    {
        try {
            var statement= connection.createStatement();
            List returnedPlayers = new ArrayList<Player>();
            var result = statement.executeQuery(String.format("SELECT * FROM PLAYERS WHERE Nation = '%s'", nation));
            while(result.next()) {
                var playerName = result.getString("Name");
                var version = result.getString("Version");
                var position = result.getString("Position");
                var club = result.getString("Club");
                var price = result.getDouble("Price");
                var nation1 = result.getString("Nation");

                var temp = new Player(playerName, version, position, nation1, club, price);
                returnedPlayers.add(temp);
            }
            return returnedPlayers;

        } catch (Exception e) {
        }
        return null;
    }

    public List<Player> getPlayersByClub(String club)
    {
        try {
            var statement= connection.createStatement();
            List returnedPlayers = new ArrayList<Player>();
            var result = statement.executeQuery(String.format("SELECT * FROM PLAYERS WHERE Club = '%s'", club));
            while(result.next()) {
                var playerName = result.getString("Name");
                var version = result.getString("Version");
                var position = result.getString("Position");
                var club1 = result.getString("Club");
                var price = result.getDouble("Price");
                var nation = result.getString("Nation");

                var temp = new Player(playerName, version, position, nation, club1, price);
                returnedPlayers.add(temp);
                return returnedPlayers;
            }
            return returnedPlayers;

        } catch (Exception e) {
        }
        return null;
    }

    public List<Player> getPlayersByVersion(String version)
    {
        try {
            var statement= connection.createStatement();
            List returnedPlayers = new ArrayList<Player>();
            var result = statement.executeQuery(String.format("SELECT * FROM PLAYERS WHERE Version = '%s'", version));
            while(result.next()) {
                var playerName = result.getString("Name");
                var version1 = result.getString("Version");
                var position = result.getString("Position");
                var club = result.getString("Club");
                var price = result.getDouble("Price");
                var nation = result.getString("Nation");

                var temp = new Player(playerName, version1, position, nation, club, price);
                returnedPlayers.add(temp);
                
            }
            return returnedPlayers;
        } catch (Exception e) {
        }
        return null;
    }

    
}
