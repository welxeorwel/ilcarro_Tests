package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class UserHelper extends HelperBase {
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.cssSelector("[href='/login?url=%2Fsearch']"));
    }

    public void fillLoginForm(String login, String password) {
        type(By.xpath("//*[@id='email']"), login);
        type(By.xpath("//*[@id='password']"), password);
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//*[@id='email']"), user.getEmail());
        type(By.xpath("//*[@id='password']"), user.getPassword());
    }


    public String checkMessage() {
        new WebDriverWait(wd, 5)
                .until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));
        String checkMessage = wd.findElement(By.cssSelector(".dialog-container h2")).getText();
        return checkMessage;
    }

    public boolean isLogoutPresent() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationform(String name, String lastName, String email, String password) {
        type(By.id("name"), name);
        type(By.id("lastName"), lastName);
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillRegistrationform(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastname());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void privacyPolicy() {
        click(By.cssSelector("label[for='terms-of-use']"));
    }

    public void checkPolicyXY() {
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

        Rectangle rect = label.getRect();
        int offSetX = rect.getWidth() / 2;
        int offSetY = rect.getHeight() / 2;

        Actions actions = new Actions(wd);
        actions.moveToElement(label).release().perform();
        actions.moveByOffset(-offSetX, -offSetY).click().release().perform();
    }

    public boolean isErrorPasswordDisplayedSize() {
        Boolean firstChild = new WebDriverWait(wd, 5)
                .until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector("div.error div:first-child"))
                        , "Password must contain minimum 8 symbols"));
        return firstChild;
    }

    public boolean isErrorPasswordDisplayedFormat() {
        Boolean lastChild = new WebDriverWait(wd, 5)
                .until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector("div.error div:last-child"))
                        , "Password must contain 1 uppercase letter, 1 lowercase letter and one number"));
        return lastChild;
    }

    public boolean isYallaButtonInactive() {
        return wd.findElement(By.cssSelector("[type*='submit']")).isEnabled();
    }

    public boolean isYallaButtonInactiveClickable() {
        return isElementPresent(By.cssSelector("button[disabled]"));
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        submitOkButton();
    }

    @AfterMethod
    public void submitOkButton() {
        click(By.xpath("//button[text()='Ok']"));
    }

}
