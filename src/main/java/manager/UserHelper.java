package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.cssSelector("[href='/login?url=%2Fsearch']"));
    }
    public void fillLoginForm(String login, String password){
        type(By.xpath("//*[@id='email']"), login);
        type(By.xpath("//*[@id='password']"),password);
    }
    public void submitLogin(){
        click(By.cssSelector("[type*='submit']"));
        click(By.xpath("//button[text()='Ok']"));
    }
public boolean isLoginSuccess(){
return isElementPresent(By.xpath("//*[text()=' Logout ']"));
}
}
