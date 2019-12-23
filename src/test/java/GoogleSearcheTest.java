import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import ex.GoogleParseException;
import model.*;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoogleSearcheTest {
    @Test
    public void testGetDocumentOneParam(){
        List<String> queries= new ArrayList<String>();
        queries.add("бизнес");
        queries.add("бизнес идеи");
        GoogleSearch googleSearch = new GoogleSearch();
        Document document = null;

        for (String s:queries) {
            googleSearch.setSearchQuery(s);
            try {
                document = googleSearch.getDocument(0);
                Assert.assertNotNull(document);
                System.out.println("******Запрос******");
                System.out.println(s);
                System.out.println(document.title());
                System.out.println(document.charset());
                System.out.println(document.location());

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    @Test
    public void testGetDocumentsListOne() {
        GoogleSearch googleSearch = new GoogleSearch();
        googleSearch.setDepth(0);
        googleSearch.setSearchQuery("бизнес разведение кроликов");
        try {
            googleSearch.search();
        }catch (IOException e){
            e.printStackTrace();
        } catch (GoogleParseException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(googleSearch.getDocumentList());
        //Assert.assertEquals(googleSearch.getDocumentList().size(),1);
        Assert.assertNotNull(googleSearch.getDocumentList().get(0));
        try {
            googleSearch.search();
        } catch (IOException e){
            e.printStackTrace();
        } catch (GoogleParseException e) {
            e.printStackTrace();
        }
        SearchResults sr = googleSearch.getSearchResults();

        for (Map.Entry<Integer, model.SearchSite> entry:sr.getMap().entrySet()){
            //System.out.println("***********");
            //System.out.print("->");
            //System.out.println(entry.getKey());
            System.out.println(entry.getKey()+" -> "+entry.getValue().getLink());
            System.out.println(entry.getValue().getUrl());
        }
    }
    @Test
    public void testGetDocumentsListTen() {
        GoogleSearch googleSearch = new GoogleSearch();
        googleSearch.setDepth(100);
        googleSearch.setSearchQuery("бизнес идеи ");
        try {
            googleSearch.search();
        }catch (IOException e){
            e.printStackTrace();
        } catch (GoogleParseException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(googleSearch.getDocumentList());
        Assert.assertEquals(googleSearch.getDocumentList().size(), 10);
        for(Document d:googleSearch.getDocumentList()) {
            System.out.println("*****Запрос*****");
            System.out.println(d.title());
            System.out.println(d.location());

            Assert.assertNotNull(d);
        }
    }
}
