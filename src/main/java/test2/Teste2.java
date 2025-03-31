package test2;

import java.io.IOException;
import java.util.List;

public class Teste2 {
    private static final String PDF_PATH = "downloads/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
    private static final String CSV_PATH = "Rol_Procedimentos.csv";
    private static final String ZIP_PATH = "Teste_Maylis.zip";

    public static void main(String[] args) {
        try {
            List<String[]> tableData = PdfExtractor.extractTableFromPDF(PDF_PATH);

            CsvGenerator.saveToCSV(tableData, CSV_PATH);

            ZipCompressor.zipFile(CSV_PATH, ZIP_PATH);

            System.out.println("Processo concluído com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao processar o PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
