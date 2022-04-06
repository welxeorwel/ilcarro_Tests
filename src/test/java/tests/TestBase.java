package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static ApplicationManager applicationManager = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        applicationManager.init();
    }

    @AfterSuite
    public void tearDown() {
        applicationManager.tearDown();
    }


}