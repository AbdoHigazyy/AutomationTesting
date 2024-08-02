package StepDef;


import Pages.P03_homePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static StepDef.TestBase.driver;

public class D04_searchStepDef {
    P03_homePage homePage;
    public SoftAssert softAssert;


    public D04_searchStepDef() {
    }
    @Before
    public void preConditions() {
        homePage = new P03_homePage(driver);
    }
    @Given("user navigates to the homepage")
    public void userNavigatesToTheHomepage() {
        homePage.deepLinkToHomePage();
    }


    @When("user searches for {string}")
    public void userEnterWord(String product_name) {
        homePage.EnterSearchWord(product_name);
        homePage.ClickOnSearchButton();
    }


    @Then("user get products that contains the word {string} successfully")
    public void userGetProductsThatContainsTheWordSuccessfully(String product_name) {
        homePage.VerifySearchResultsByProductName(product_name);
    }
    @And("each result should contain the search word {string}")
    public void eachResultShouldContainTheSearchWord(String product_name) {
        homePage.searchResultItemContainTheWorrd();
    }

    @When("user searches for SKU {string}")
    public void userEnterSku(String SKU) {
        homePage.EnterSearchWord(SKU);
        homePage.ClickOnSearchButton();
    }

    @And("click on the product in search result")
    public void userClickOnTheProductAfterClickingOnSearchButton() {
        homePage.ClickOnSearchResult();
    }

    @Then("SKU on product page should match the searched SKU {string}")
    public void userGetTheProductThatContainsTheSkuSuccessfully(String SKU) {
       homePage.VerifySearchResultsBySKU(SKU);
    }


}
