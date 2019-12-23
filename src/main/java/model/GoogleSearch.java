package model;

import ex.GoogleParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleSearch {
    private SearchResults searchResults;
    private static final String SEARCH_URL="https://www.google.com/search?q=%s&start=%d";
    private String searchQuery;
    private int depth;
    private int currentPages;
    private List<Document> documentList;

    public List<Document> getDocumentList() {
        return documentList;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public SearchResults getSearchResults() {
        return searchResults;
    }

    public GoogleSearch() {
        this.searchResults = new SearchResults();
        setSearchQuery("");
        setDepth(0);
    }

    public void setSearchQuery(String searchQuery) {
        this.searchResults = new SearchResults();
        this.searchQuery = searchQuery;
    }
    public void search() throws IOException, GoogleParseException {
        documentList = new ArrayList<Document>();
        if (getDepth()==0){
            documentList.add(getDocument());
        } else{
            while (this.currentPages <getDepth()){
                documentList.add(getDocument(this.currentPages));
                this.currentPages +=10;
            }
        }
        for (Document d:documentList){
            Element content = d.getElementById("search");
            Elements bkWMgd_s = content.getElementsByClass("bkWMgd");
            for(Element curentBkWMgd:bkWMgd_s){
                Elements items = content.getElementsByClass("rc");
                for (Element item:items) {
                    this.searchResults.add(new SearchSite(item.html()));
                }

            }
/*
            Elements items = content.getElementsByClass("rc");
            for (Element item:items) {
                this.searchResults.add(new SearchSite(item.html()));
            }
*/
        }
    }

    public GoogleSearch(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    public Document getDocument(String searchQuery, int page) throws IOException {
        this.searchQuery = searchQuery;
        Document document = Jsoup.connect(String.format(SEARCH_URL, searchQuery.replace(' ','+'), page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .get();
        return document;
    }
    public Document getDocument(int page) throws IOException {
        return getDocument(getSearchQuery(), page);
    }
    public Document getDocument() throws IOException {
        return getDocument(getSearchQuery(), getDepth());
    }

}
