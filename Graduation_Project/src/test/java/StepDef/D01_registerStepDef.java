package StepDef;

import Pages.P01_register;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class D01_registerStepDef {

    P01_register registerPage ;

  @Before
  public void precondition()
  {
      registerPage = new P01_register(TestBase.driver);
  }

    @Given("user go to register page")
    public void userGoToRegisterPage()
    {
registerPage.deepLinkToRegisterPage();
    }

    @And("user select gender type")
    public void userSelectGenderType() {
      registerPage.selectMaleGender();
    }

    @And("user enter first name {string} and last name {string}")
    public void userEnterFirstNameAndLastName(String firstname, String lastname) {
      registerPage.typeFirstAndLastName(firstname , lastname);
    }

    @And("user enter date of birth")
    public void userEnterDateOfBirth() {
        registerPage.EnterDate("19", "September", "2000");

    }

    @And("user enter email {string} field")
    public void userEnterEmailField(String email) {
        registerPage.typeEmail(email);
    }

    @And("user fills Password fields {string} {string}")
    public void userFillsPasswordFields(String Password, String confirmPassword) {
      registerPage.fillPasswordFields(Password , confirmPassword);
        
    }

    @And("user clicks on register button")
    public void userClicksOnRegisterButton() {
      registerPage.clickRegisterButton();
        
    }

    @Then("success message is displayed")
    public void successMessageIsDisplayed() {
      registerPage.validateSuccessfulRegisterationProccess();
    }
}
