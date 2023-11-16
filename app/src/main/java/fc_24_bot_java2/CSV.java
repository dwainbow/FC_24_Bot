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
    private List<List<Integer>> rows;

    public CSV() {
        this.headers = new ArrayList<>(List.of("Successes", "Failures", "Error"));
        this.rows = new ArrayList<>();
    }

    public void addRow(Integer... values) {
        this.rows.add(Arrays.asList(values));
    }

    public void updateRow(int rowIndex, int ... newValues) {
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            List<Integer> row = rows.get(rowIndex);
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

            for (List<Integer> row : rows) {
                writeRowValues(writer, row);
            }

            System.out.println("CSV file updated successfully.");
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
     private void writeRowValues(BufferedWriter writer, List<Integer> row) throws IOException {
        for (Integer value : row) {
            writer.write(value);
            writer.write(",");
        }
        writer.newLine();
    }

}
    

    

