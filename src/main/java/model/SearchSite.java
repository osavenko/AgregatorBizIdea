package model;

import ex.GoogleParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
import java.net.URISyntaxException;

public class SearchSite {
    private String url;
    private String link;
    private String title;
    private String original;

    public String getOriginal() {
        return original;
    }

    public SearchSite() {
    }

    public SearchSite(String original) throws GoogleParseException {
        this.original = original;
        parse();
    }

    public SearchSite(String url, String link, String title) {
        this.url = url;
        this.link = link;
        this.title = title;
    }
    private void parse() throws GoogleParseException {
        String html = this.original;
        Document doc = Jsoup.parse(html);
        Elements links = doc.getElementsByTag("a");
        if(!links.isEmpty()){
            this.link = links.get(0).attr("href");
        } else {
            throw new GoogleParseException();
        }
        try {
            URI uri = new URI(this.link);
            this.url = uri.getHost();
        } catch (URISyntaxException e) {
            //e.printStackTrace();
            throw new GoogleParseException();
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
