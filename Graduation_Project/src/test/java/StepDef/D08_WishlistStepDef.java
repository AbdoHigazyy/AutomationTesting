package StepDef;

import Pages.P03_homePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class D08_WishlistStepDef {
    P03_homePage homePage;

    public D08_WishlistStepDef() {
    }

    @Given("user go to home page")
    public void userGoToHomePage() {
        homePage.deepLinkToHomePage();
    }
    @Before
    public void preConditions() {
        homePage = new P03_homePage(TestBase.driver);
    }

    @When("add any product {string} to the wishlist by clicking on the love icon")
    public void addAnyProductToTheWishlistByClickingOnTheLoveIcon(String Product) {
        homePage.clickOnLoveIconOfSpecificProduct(Product);
    }

    @Then("success message is displayed on the top of the page")
    public void successMessageIsDisplayedOnTheTopOfThePage() {
        homePage.verifyDisplayASuccessfulMessageWhenClickOnAddToWishList();
    }

    @Then("wishlist counter is more than zero items")
    public void wishlistCounterIsMoreThanZeroItems() {
        homePage.verifyTheWishlistCounter();
    }


}
