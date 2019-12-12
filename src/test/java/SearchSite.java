import org.junit.Assert;
import org.junit.Test;

public class SearchSite {
    @Test
    public void testCreateObject(){
        SearchSite searchSite = new SearchSite();
        Assert.assertNotNull(searchSite);
    }
}
