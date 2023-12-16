package fc_24_bot_java2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSV {
    private static final String CSV_FILE_PATH = "BotData.csv";
    private static CSV instance;
    private List<String> headers;
    private List<List<String>> rows;

    public static CSV getInstance(){
        if (instance == null){
            instance = new CSV();
        }
        return instance;
    }


    private CSV() {
        this.headers = new ArrayList<>(List.of("Successes", "Failures"));
        this.rows = readFromFile();
        addRow("0", "0");
        writeToFile();

    }
    public int getNumRows()
    {
        return rows.size();
    }

    private List<List<String>> readFromFile() {
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void addRow(String... values) {
        this.rows.add(Arrays.asList(values));
        writeToFile();
    }

    public void updateRow(int rowIndex, String... newValues) {
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

    private void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            // writeRow(writer, headers);

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
