package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector(".pac-item"));

    }

    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {
        typeCity(city);
        String[] from = dataFrom.split("/");
        String[] to = dataTo.split("/");
        click(By.id("dates"));
        click(By.xpath("//div [.=' " + from[1] + " ']"));
        click(By.xpath("//div [.=' " + to[1] + " ']"));
    }

    public boolean isPriceRangePresent() {

        return isElementPresent(By.xpath("//*[text()='Sort by price:']"));
    }

    private void typePeriodInPast(String dataFrom, String dataTo) {
        //wd.findElement(By.id("dates")).sendKeys(dataFrom + " - "+dataTo);
        type(By.id("dates"),dataFrom + " - "+dataTo);
        click(By.cssSelector(".cdk-overlay-container"));

    }
    public void searchPeriodInPast(String city, String dataFrom, String dataTo) {
        typeCity(city);
        typePeriodInPast(dataFrom, dataTo);
    }
    public void searchAnyPeriod(String city, String dataFrom, String dataTo) {
        typeCity(city);
        selectPeriodAnyData(dataFrom, dataTo);
    }

    private void selectPeriodAnyData(String dataFrom, String dataTo) {
        LocalDate now = LocalDate.now();
        // now.getYear();//int
        // now.getMonth();//int
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        click(By.id("dates"));

        int diffMonth = from.getYear() - now.getYear()
                == 0 ? from.getMonthValue() - now.getMonthValue() :
                12 - now.getMonthValue() + from.getMonthValue();

        //  diffYear = from.getYear() - now.getYear();
        // if (diffYear == 0) {
        //    diffMonth = from.getMonthValue() - now.getMonthValue();
        //} else {
        //   diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        //}
        clickByNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));
        diffMonth = to.getYear() - from.getYear()
                == 0 ? to.getMonthValue() - from.getMonthValue() :
                12 - from.getMonthValue() + from.getMonthValue();

        //diffYear = to.getYear() - from.getYear();
        //if (diffYear == 0) {
        //    diffMonth = to.getMonthValue() - from.getMonthValue();
        //} else {
        //    diffMonth = 12 - from.getMonthValue() + from.getMonthValue();
        // }
        clickByNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickByNextMonth(int count) {
        for (int i = 0; i < count; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));
        }
    }

    public void openSearchForm() {
        click(By.id("0"));
    }

    public boolean isPeriodInPast() {
        pause(1000);
        WebElement el = wd.findElement(By.xpath("//div[@class='ng-star-inserted']"));
        String error = el.getText();
        System.out.println(error);
        return error.equals("You can't pick date before today");
    }


}
