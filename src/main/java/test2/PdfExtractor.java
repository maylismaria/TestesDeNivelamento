package test2;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfExtractor {
    public static List<String[]> extractTableFromPDF(String pdfPath) throws IOException {
        List<String[]> tableData = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper() {
                    @Override
                    protected void processTextPosition(TextPosition text) {
                        super.processTextPosition(text);
                    }
                };

                stripper.setSortByPosition(true);

                String text = stripper.getText(document);

                String[] lines = text.split("\n");

                for (String line : lines) {
                    line = line.trim();

                    if (!line.isEmpty()) {
                        String[] columns = line.split("\\s{2,}");

                        tableData.add(columns);
                    }
                }
            }
        }
        return tableData;
    }
}
