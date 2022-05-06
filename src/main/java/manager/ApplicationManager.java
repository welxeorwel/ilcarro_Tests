package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    UserHelper userhelper;
    CarHelper car;
    HelperSearch search;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init() {
        wd = new EventFiringWebDriver(new ChromeDriver());
        logger.info("test starts in chrome driver");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.xyz/");
        userhelper = new UserHelper(wd);
        car = new CarHelper(wd);
        search = new HelperSearch(wd);
        wd.register(new MyListener());
    }

    public void tearDown() {
        wd.quit();
    }

    public UserHelper getUserhelper() {
        return userhelper;
    }

    public CarHelper car() {
        return car;
    }

    public HelperSearch search() {
        return search;
    }
}
