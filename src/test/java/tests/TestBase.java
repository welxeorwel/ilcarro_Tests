package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected static ApplicationManager applicationManager = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        applicationManager.init();
    }

    @AfterMethod
    public void tearDown() {
        applicationManager.tearDown();
    }


}