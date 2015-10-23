package service;

import entity.Group;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Scraper implements Callable<String> {

    private String url;

    public Scraper(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        Document doc = new Document("");
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
        }
        Elements a = doc.select("#authors");
        Elements c = doc.select("#class");
        Elements g = doc.select("#group");
        String authors = a.text();
        String cla = c.text();
        String group = g.text();
        return authors + "#" + cla + "#" + group;
    }
}
