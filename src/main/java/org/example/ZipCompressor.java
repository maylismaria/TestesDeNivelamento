package org.example;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor {

    // Método para compactar os arquivos em ZIP
    public static void zipFiles(String folderPath, String zipFilePath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".pdf"));

        if (files == null || files.length == 0) {
            System.out.println("Nenhum arquivo PDF encontrado para compactar.");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                    System.out.println("Arquivo compactado: " + file.getName());
                } catch (IOException e) {
                    System.err.println("Erro ao compactar o arquivo: " + file.getName());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo ZIP: " + e.getMessage());
            throw e;  // Re-throw para ser tratado na classe principal
        }
    }
}

