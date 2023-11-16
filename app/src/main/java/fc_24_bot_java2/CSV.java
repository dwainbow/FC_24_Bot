package fc_24_bot_java2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
    private static final String CSV_FILE_PATH = "BotData.csv";

    private List<String> headers;
    private List<List<String>> rows;

    public CSV() {
        this.headers = new ArrayList<>(List.of("Successes", "Failures"));
        this.rows = new ArrayList<>();
    }

    public void addRow(String... values) {
        this.rows.add(Arrays.asList(values));
    }

    public void updateRow(int rowIndex, String ... newValues) {
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            List<String> row = rows.get(rowIndex);
            for (int i = 0; i < Math.min(row.size(), newValues.length); i++) {
                row.set(i, newValues[i]);
            }
            writeToFile();
        } else {
            System.out.println("Invalid row index");
        }
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writeRow(writer, headers);

            for (List<String> row : rows) {
                writeRow(writer, row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRow(BufferedWriter writer, List<String> row) throws IOException {
        for (String value : row) {
            writer.write(value);
            writer.write(",");
        }
        writer.newLine();
    }

}
    

    

