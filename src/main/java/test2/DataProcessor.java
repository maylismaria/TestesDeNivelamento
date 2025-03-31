package test2;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    public static List<String[]> processTableData(List<String[]> rawData) {
        List<String[]> processedData = new ArrayList<>();

        for (String[] row : rawData) {
            for (int i = 0; i < row.length; i++) {
                row[i] = row[i].replace("OD", "Seg. Odontológica")
                        .replace("AMB", "Seg. Ambulatorial");
            }
            processedData.add(row);
        }
        return processedData;
    }
}
