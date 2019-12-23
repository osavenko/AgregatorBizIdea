package command;

import ex.GoogleParseException;
import model.GoogleSearch;
import model.SearchResults;
import model.SearchSite;

import java.io.IOException;
import java.util.Map;

public class ReciveFromGoogle implements Command{
    private int depth;
    private String searchQuery;

    public ReciveFromGoogle(String searchQuery, int depth) {
        this.depth = depth;
        this.searchQuery = searchQuery;
    }

    public void execute() {
        GoogleSearch googleSearch = new GoogleSearch();
        googleSearch.setSearchQuery(this.searchQuery);
        googleSearch.setDepth(this.depth);
        try {
            googleSearch.search();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GoogleParseException e) {
            e.printStackTrace();
        }
        SearchResults result = googleSearch.getSearchResults();
        for (Map.Entry<Integer, SearchSite> entry:result.getMap().entrySet()){
            System.out.println(entry.getKey()+" -> "+entry.getValue().getUrl());
            System.out.println("            "+entry.getValue().getLink());
        }
    }
}
