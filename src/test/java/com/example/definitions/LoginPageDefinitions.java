package com.example.definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginPageDefinitions {

   Hooks hooks;


    @Given("User is on HRMLogin page {string}")
    public void loginTest(String url) {

        hooks.driver.get(url);

    }

    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {

        // login to application
        hooks.driver.findElement(By.name("username")).sendKeys(userName);
        hooks.driver.findElement(By.name("password")).sendKeys(passWord);
        hooks.driver.findElement(By.xpath("//*[@class='oxd-form']/div[3]/button")).submit();

        // go the next page
    }

    @Then("User should be able to login successfully and new page open")
    public void verifyLogin() {

        String homePageHeading = hooks.driver.findElement(By.xpath("//*[@class='oxd-topbar-header-breadcrumb']/h6")).getText();

        //Verify new page - HomePage
        Assert.assertEquals("Dashboard",homePageHeading);

    }

    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {

        String actualErrorMessage = hooks.driver.findElement(By.xpath("//*[@class='orangehrm-login-error']/div[1]/div[1]/p")).getText();

        // Verify Error Message
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage);

    }

}
