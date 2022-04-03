package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends TestBase {
    @Test
    public void searchCurrentMonth() {
        applicationManager.search().searchCurrentMonth("Tel-Aviv", "4/10/21", "4/20/21");
        applicationManager.search().submit();
        Assert.assertTrue(applicationManager.search().isPriceRangePresent());
    }

    @Test
    public void searchCurrentMonthPast() {
        applicationManager.search().searchCurrentMonthPast("Tel-Aviv", "1/10/21", "4/20/21");
        Assert.assertFalse(applicationManager.search().isYallaBattonNotActive());
    }
}
