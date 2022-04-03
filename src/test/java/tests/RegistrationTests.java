package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (applicationManager.getUserhelper().isLogoutPresent()) {
            applicationManager.getUserhelper().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        int index = (int) (System.currentTimeMillis() / 1000) % 1000;
        User user = new User()
                .setName("Bobik")
                .setLastName("Bob")
                .setEmail("bobik" + index + "@gmail.com")
                .setPassword("Bobik12345");
        applicationManager.getUserhelper().openRegistrationForm();
        applicationManager.getUserhelper().fillRegistrationform(user);
        applicationManager.getUserhelper().checkPolicyXY();
        applicationManager.getUserhelper().submit();
        Assert.assertEquals(applicationManager.getUserhelper().checkMessage(), "You are logged in success");
        applicationManager.getUserhelper().submitOkButton();
    }

    @Test
    public void registrationWrongPass() {

        User user = new User()
                .setName("Bobik")
                .setLastName("Bob")
                .setEmail("bobikbobo@gmail.com")
                .setPassword("12345");
        applicationManager.getUserhelper().openRegistrationForm();
        applicationManager.getUserhelper().fillRegistrationform(user);
        applicationManager.getUserhelper().checkPolicyXY();
        applicationManager.getUserhelper().submit();
        Assert.assertTrue(applicationManager.getUserhelper().isErrorPasswordDisplayedSize());
        Assert.assertTrue(applicationManager.getUserhelper().isErrorPasswordDisplayedFormat());
        Assert.assertFalse(applicationManager.getUserhelper().isYallaButtonInactive());
        Assert.assertTrue(applicationManager.getUserhelper().isYallaButtonInactiveClickable());
    }


}
