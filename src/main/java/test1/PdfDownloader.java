package test1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.hc.client5.http.fluent.Request;

public class PdfDownloader {
    public static String downloadFile(String fileURL, String downloadFolder) throws IOException {
        String fileName = downloadFolder + fileURL.substring(fileURL.lastIndexOf("/") + 1);
        File file = new File(fileName);


        if (file.exists()) {
            System.out.println("Arquivo já existe: " + fileName);
        }

        try {
            System.out.println("Baixando: " + fileURL);
            byte[] fileData = Request.get(fileURL).execute().returnContent().asBytes();
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(fileData);
                return fileName;
            }
        } catch (IOException e) {
            System.err.println("Erro ao baixar o arquivo: " + fileURL);
            throw e;
        }
    }
}
