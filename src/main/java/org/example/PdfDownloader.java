package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.hc.client5.http.fluent.Request;

public class PdfDownloader {
    public static String downloadFile(String fileURL, String downloadFolder) throws IOException {
        String fileName = downloadFolder + fileURL.substring(fileURL.lastIndexOf("/") + 1);
        File file = new File(fileName);

        // Verifica se o arquivo já existe
        if (file.exists()) {
            System.out.println("Arquivo já existe: " + fileName);
            return null; // Retorna null se o arquivo já existir
        }

        try {
            System.out.println("Baixando: " + fileURL);
            byte[] fileData = Request.get(fileURL).execute().returnContent().asBytes();
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(fileData);
                return fileName; // Retorna o caminho do arquivo se for baixado com sucesso
            }
        } catch (IOException e) {
            System.err.println("Erro ao baixar o arquivo: " + fileURL);
            throw e;  // Repassa a exceção para ser tratada na classe chamadora
        }
    }
}
