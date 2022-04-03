package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (applicationManager.getUserhelper().isLogoutPresent()) {
            applicationManager.getUserhelper().logout();
        }
    }

    @Test
    public void loginSuccess() {
        applicationManager.getUserhelper().openLoginForm();
        applicationManager.getUserhelper().fillLoginForm("bobik@gmail.com", "Bobik123");
        applicationManager.getUserhelper().submit();
        applicationManager.getUserhelper().pause(1000);
        Assert.assertEquals(applicationManager.getUserhelper().checkMessage(), "Logged in success");
    }

    @Test
    public void loginSuccessNew() {
        User user = new User().setEmail("bobik@gmail.com").setPassword("Bobik123");
        applicationManager.getUserhelper().openLoginForm();
        applicationManager.getUserhelper().fillLoginForm(user);
        applicationManager.getUserhelper().submit();
        applicationManager.getUserhelper().pause(1000);
        Assert.assertEquals(applicationManager.getUserhelper().checkMessage(), "Logged in success");
    }

    @AfterMethod
    public void postCondition(){
        applicationManager.getUserhelper().submitOkButton();
    }
}