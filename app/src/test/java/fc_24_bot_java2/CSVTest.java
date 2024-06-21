package fc_24_bot_java2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    private static final String TEST_CSV_FILE_PATH = "TestBotData.csv";
    private CSV csv;

    @BeforeEach
    void setUp() {
        // Create a test CSV file
        createTestCSVFile();

        // Initialize the CSV instance
        csv = CSV.getInstance();
    }

    @Test
    void testGetNumRows() {
        assertEquals(1, csv.getNumRows());
    }

    @Test
    void testUpdateRow() {
        csv.updateRow(0, "1", "2");
        assertEquals(List.of("1", "2"), csv.getRows().get(0));
    }

    private void createTestCSVFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_FILE_PATH))) {
            writer.write("Successes,Failures");
            writer.newLine();
            writer.write("0,0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}