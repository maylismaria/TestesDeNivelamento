package org.example;

import java.io.File;
import java.io.IOException;

public class WebScraper {

    private static final String TARGET_URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
    private static final String DOWNLOAD_FOLDER = "downloads/";
    private static final String ZIP_FILE = "Anexos.zip";

    public static void main(String[] args) {
        try {
            new File(DOWNLOAD_FOLDER).mkdirs(); // Criar diretório para os downloads

            String[] pdfLinks = PdfLinkFinder.findPdfLinks(TARGET_URL);
            if (pdfLinks == null || pdfLinks.length == 0) {
                System.out.println("Nenhum link de PDF encontrado.");
                return;
            }

            for (String link : pdfLinks) {
                PdfDownloader.downloadFile(link, DOWNLOAD_FOLDER);
            }

            ZipCompressor.zipFiles(DOWNLOAD_FOLDER, ZIP_FILE);
            System.out.println("Processo concluído! PDFs baixados e compactados.");
        } catch (IOException e) {
            System.err.println("Erro ao processar os arquivos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
