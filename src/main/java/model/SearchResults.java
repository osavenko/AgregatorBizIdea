package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResults {
    private Map<Integer, SearchSite> map = new HashMap<Integer, model.SearchSite>();

    public SearchResults() {

    }

    public void add(SearchSite searchSite){
        map.put(map.size(),searchSite);
    }

    public Map<Integer, SearchSite> getMap() {
        return map;
    }
}
