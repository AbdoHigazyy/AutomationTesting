package StepDef;

import Pages.P02_login;
import Pages.P03_homePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class D03_currenciesStepDef {
    @Before
    public void precondition() {
        homePage = new P03_homePage(TestBase.driver);
    }

    P03_homePage homePage;

    public void userGoToHomePage() {
        homePage.deepLinkToHomePage();
    }

    @And("user select dropdown list on the top left of home page")
    public void userSelectDropDownList() {
        homePage.DropDownList();

    }

    @And("user select Euro Currency")
    public void userSelectEuroCurrency() {
        homePage.selectEuro();
    }

    @Then("Euro Symbol \\(€) is shown on the  products displayed in Home page")
    public void verifyEuroSymbolOnProducts() {
        List<WebElement> productPrices = homePage.getProductPrices();
        for (WebElement price : productPrices) {
            String priceText = price.getText();
            Assert.assertTrue(priceText.contains("€"), "Price does not contain Euro symbol: " + priceText);
        }
    }


}
