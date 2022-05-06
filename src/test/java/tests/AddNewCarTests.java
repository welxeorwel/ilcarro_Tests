package tests;

import manager.MyDataProvider;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {
    @BeforeMethod
    public void precondition(){
        if(!applicationManager.getUserhelper().isLogoutPresent()){
            applicationManager.getUserhelper().login(new User().setEmail("bobik@gmail.com").setPassword("Bobik123"));
            applicationManager.getUserhelper().pause(500);
        }
    }

    @Test
    public void addNewCarSuccess(){
        int index = (int) (System.currentTimeMillis() / 1000) % 3600;
        Car car = Car.builder()
                .address("Tel Aviv, Israel")
                .make("KIA")
                .model("Rio")
                .year("2022")
                .engine("2.5")
                .fuel("Petrol")
                .gear("SMT")
                .wD("AWD")
                .doors("4")
                .seats("5")
                .carClass("C")
                .fuelConsumption("6.5")
                .carRegistrationNumber("100-22"+index)
                .price("42")
                .distanceIncluded("5000")
                .features("Text of features")
                .about("Do you like what you see?")
                .build();
        applicationManager.car().openCarForm();
        applicationManager.car().fillCarForm(car);
        applicationManager.car().attachPhoto("C:\\Windows.old\\Users\\welxeor\\projects\\ilcarro_Tests\\download.jpg");
        applicationManager.car().submit();
        Assert.assertTrue(applicationManager.car().isCarAdded());
        //login-->car added
    }

    @Test(dataProvider = "validDataCar", dataProviderClass = MyDataProvider.class)
    public void addNewCarSucsessCSV(Car car){
        applicationManager.car().openCarForm();
        applicationManager.car().fillCarForm(car);
        applicationManager.car().attachPhoto("C:\\Windows.old\\Users\\welxeor\\projects\\ilcarro_Tests\\download.jpg");
        applicationManager.car().submit();
        Assert.assertTrue(applicationManager.car().isCarAdded());
    }
}
