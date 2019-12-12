import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.GoogleSearch;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        googleSearch.setSearchQuery("бизнес идеи");
        try {
            googleSearch.search();
        }catch (IOException e){
            e.printStackTrace();
        }
        Assert.assertNotNull(googleSearch.getDocumentList());
        Assert.assertEquals(googleSearch.getDocumentList().size(),1);
        Assert.assertNotNull(googleSearch.getDocumentList().get(0));
    }
    @Test
    public void testGetDocumentsListTen() {
        GoogleSearch googleSearch = new GoogleSearch();
        googleSearch.setDepth(100);
        googleSearch.setSearchQuery("бизнес идеи");
        try {
            googleSearch.search();
        }catch (IOException e){
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
