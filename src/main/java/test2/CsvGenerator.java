package test2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvGenerator {
    public static void saveToCSV(List<String[]> data, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : data) {
                writer.append(String.join(";", row));
                writer.append("\n");
            }
        }
    }
}
