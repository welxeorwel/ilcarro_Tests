package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener extends AbstractWebDriverEventListener {
Logger logger = LoggerFactory.getLogger(MyListener.class);

public MyListener(){super();}
//aga
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        logger.info("'Start find' element ---> " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        logger.info("'The element with locator' -->" + by + " was found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("We have a problem-->" + throwable.getMessage());
        logger.info("We have a problem-->" + throwable.fillInStackTrace());
        int index = (int) (System.currentTimeMillis() / 1000) / 3600;
        HelperBase helperBase = new HelperBase(driver);
        helperBase.takeScreenShot("src/tests/screenshots");
        logger.info("This is the link to screen with problem -->src/test/screenshots/screen-" + index + ".png");

    }

}
