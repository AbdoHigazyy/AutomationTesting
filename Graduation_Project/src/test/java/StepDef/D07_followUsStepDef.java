package StepDef;

import Pages.P03_homePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class D07_followUsStepDef {
    P03_homePage homePage;

    public D07_followUsStepDef() {
    }

    @Before
    public void preConditions() {
        homePage = new P03_homePage(TestBase.driver);
    }

    @When("user opens {string} link")
    public void userOpensLink(String Channel) {
        homePage.clickOnChannel(Channel);
    }

    @Then("{string} is opened in new tab")
    public void isOpenedInNewTab(String Link) {
        homePage.verifyChannelLinks(Link);
    }
}
