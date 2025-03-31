package test1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class PdfLinkFinder {

    public static String[] findPdfLinks(String url) throws IOException {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.select("a[href$=.pdf]").stream()
                    .map(element -> element.absUrl("href"))
                    .filter(link -> link.contains("Anexo_I") || link.contains("Anexo_II"))
                    .toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Erro ao buscar links de PDF: " + e.getMessage());
            throw e;
        }
    }
}
