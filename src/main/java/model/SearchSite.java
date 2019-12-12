package model;

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

    public SearchSite(String original) {
        this.original = original;
    }

    public SearchSite(String url, String link, String title) {
        this.url = url;
        this.link = link;
        this.title = title;
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
