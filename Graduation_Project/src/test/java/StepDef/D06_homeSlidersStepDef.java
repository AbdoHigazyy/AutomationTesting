package StepDef;

import Pages.P03_homePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class D06_homeSlidersStepDef {
    P03_homePage homePage;

    public D06_homeSlidersStepDef() {
    }

    @Before
    public void preConditions() {
        homePage = new P03_homePage(TestBase.driver);
    }

    @When("user go to the slider section and click on item {string} {string}")
    public void userGoToTheSliderSectionAndClickOnItem(String ItemNumber, String Item) {
        homePage.clickOnSliderItem(Item, ItemNumber);
    }

    @Then("user should redirect to the item page URL {string}")
    public void userShouldRedirectToTheItemPageURL(String URL) {
        homePage.VerifySliderItemURL(URL);
    }
}
