package StepDef;

import Pages.P01_register;
import Pages.P02_login;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class D02_loginStepDef {
    P02_login loginPage;

    @Before
    public void precondition()
    {
        loginPage = new P02_login(TestBase.driver);
    }

    @Given("user go to login page")
    public void userGoToLoginPage()
    {
        loginPage.deepLinkToLoginPage();
    }


    @And("{int} user login with valid {string} and {string}")
    public void userLoginWithValidAnd(int userNumber, String email, String password) {
        loginPage.typeEmail(email);
        loginPage.typePassword(password);
    }

    @And("{int} user press on login button")
    public void userPressOnLoginButton(int userNumber) {
        loginPage.clickLoginButton();
        
    }

    @Then("{int} user login to the system successfully")
    public void userLoginToTheSystemSuccessfully(int userNumber) {
        loginPage.userLoginSuccessfully();

    }



    @And("user login with invalid {string} and {string}")
    public void userLoginWithInvalidAnd(String invalidEmail, String password) {
        loginPage.typeEmail(invalidEmail);
        loginPage.typePassword(password);
    }

    @And("user press on login button")
    public void userPressOnLoginButton() {
        loginPage.clickLoginButton();

    }

    @Then("user could not login to the system")
    public void userCouldNotLoginToTheSystem() {
        loginPage.errorMessage();
    }
}
