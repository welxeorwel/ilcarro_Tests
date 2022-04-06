package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        applicationManager.search().openSearchForm();

    }

    @Test
    public void searchPeriodInPast() {
        applicationManager.search().searchPeriodInPast("Tel-Aviv", "2/11/2022", "4/20/2022");
        Assert.assertFalse(applicationManager.getUserhelper().isYallaButtonInactive());
        Assert.assertTrue(applicationManager.search().isPeriodInPast());
    }

    @Test(priority = 1)
    public void searchCurrentMonth() {
        applicationManager.search().searchCurrentMonth("Tel-Aviv", "6/10/22", "6/20/22");
        applicationManager.search().submit();
        Assert.assertTrue(applicationManager.search().isPriceRangePresent());
    }

    @Test(priority = 2)
    public void searchAnyPeriod() {
        applicationManager.search().searchAnyPeriod("Tel-Aviv", "02/05/2023", "04/02/2023");
        applicationManager.search().submit();

    }
}
