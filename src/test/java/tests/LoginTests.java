package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginSuccess() {
        applicationManager.getUserhelper().openLoginForm();
        applicationManager.getUserhelper().fillLoginForm("bobik@gmail.com","Bobik123");
        applicationManager.getUserhelper().submitLogin();
        Assert.assertTrue(applicationManager.getUserhelper().isLoginSuccess());
    }

}