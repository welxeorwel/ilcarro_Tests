package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperSearch extends HelperBase {
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {
        type(By.id("city"), city);
        click(By.cssSelector(".pac-item"));
        pause(500);
        String[] from = dataFrom.split("/");
        String[] to = dataTo.split("/");
        click(By.id("dates"));
        click(By.xpath("//div [.=' " + from[1] + " ']"));
        click(By.xpath("//div [.=' " + to[1] + " ']"));
    }

    public boolean isPriceRangePresent() {

        return isElementPresent(By.xpath("//*[text()='Sort by price:']"));
    }

    public void searchCurrentMonthPast(String city, String dataFrom, String dataTo) {
        type(By.id("city"), city);
        click(By.cssSelector(".pac-item"));
        pause(500);
        type(By.id("dates"), dataFrom + "-" + dataTo);
    }

    public boolean isYallaBattonNotActive() {
        return wd.findElement(By.cssSelector("[type*='submit']")).isEnabled();
    }
}
