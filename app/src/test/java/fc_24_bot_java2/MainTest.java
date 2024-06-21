package fc_24_bot_java2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    private final InputStream originalInput = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMainFlow() {
        String input = "PlayerName\nn\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Main.main(new String[]{});

        String expectedOutput = "Welcome to the FUT Trading Bot!\n" +
                "Enter the player name who you want to snipe: \n" +
                "The recommended price is 1000 , Would you like to set a max buy price? (y/n)\n" +
                "Buying: PlayerName\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testInvalidChoice() {
        String input = "PlayerName\n2\n0\nn\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Main.main(new String[]{});

        String expectedOutput = "Welcome to the FUT Trading Bot!\n" +
                "Enter the player name who you want to snipe: \n" +
                "There is more than one, which one do you want to trade? \n" +
                "0) Player1\n" +
                "1) Player2\n" +
                "Enter the choice number: \n" +
                "Invalid choice, try again\n" +
                "Enter the choice number: \n" +
                "The recommended price is 1000 , Would you like to set a max buy price? (y/n)\n" +
                "Buying: Player1\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}