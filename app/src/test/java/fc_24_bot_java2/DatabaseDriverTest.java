package fc_24_bot_java2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseDriverTest {
    private DatabaseDriver databaseDriver;

    @BeforeEach
    void setUp() {
        databaseDriver = new DatabaseDriver();
        try {
            databaseDriver.connect();
            databaseDriver.createTables();
        } catch (SQLException e) {
            fail("Failed to connect to the database or create tables");
        }
    }

    @Test
    void testAddUser() {
        try {
            databaseDriver.addUser("test@example.com", "password");
            List<Player> players = databaseDriver.getPlayersByName("test");
            assertEquals(1, players.size());
            assertEquals("test@example.com", players.get(0).getEmail());
        } catch (SQLException e) {
            fail("Failed to add user");
        }
    }

    @Test
    void testGetPlayersByName() {
        try {
            databaseDriver.addUser("test1@example.com", "password1");
            databaseDriver.addUser("test2@example.com", "password2");
            List<Player> players = databaseDriver.getPlayersByName("test");
            assertEquals(2, players.size());
            assertEquals("test1@example.com", players.get(0).getEmail());
            assertEquals("test2@example.com", players.get(1).getEmail());
        } catch (SQLException e) {
            fail("Failed to add users");
        }
    }

    // Add more tests for other methods

    @Test
    void testDisconnect() {
        try {
            databaseDriver.disconnect();
            assertTrue(databaseDriver.getConnection().isClosed());
        } catch (SQLException e) {
            fail("Failed to disconnect from the database");
        }
    }
}