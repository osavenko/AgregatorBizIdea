import ex.GoogleParseException;
import model.GoogleSearch;
import model.SearchResults;
import model.SearchSite;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GoogleSearch googleSearch = new GoogleSearch();
        googleSearch.setSearchQuery("бизнес разведение кроликов");
        googleSearch.setDepth(50);
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
