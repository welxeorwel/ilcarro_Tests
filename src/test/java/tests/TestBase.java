package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static ApplicationManager applicationManager = new ApplicationManager();
Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite
    public void setUp() {
        applicationManager.init();
    }

    @AfterSuite
    public void tearDown() {
        applicationManager.tearDown();
    }


}