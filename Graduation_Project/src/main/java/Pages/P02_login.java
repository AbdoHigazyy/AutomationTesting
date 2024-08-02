package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class P02_login {
    private WebDriver driver ;
    public P02_login(WebDriver driver){
        this.driver = driver ;
    }

    //locators
    By email_TextBox = By.id("Email");
    By password_TextBox = By.id("Password");
    By login_Button = By.cssSelector("[class=\"button-1 login-button\"]");
    By myAccount_Button = By.cssSelector("[class=\"ico-account\"]");
    By errorMessage = By.xpath("//div[contains(@class, 'message-error') and contains(@class, 'validation-summary-errors')]");

//actions
    public void deepLinkToLoginPage()
    {
        driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }
    public void typeEmail(String email)
    {
        driver.findElement(email_TextBox).sendKeys(email);
    }
    public void typePassword(String password)
    {
        driver.findElement(password_TextBox).sendKeys(password);
    }
    public void clickLoginButton()
    {
        driver.findElement(login_Button).click();
    }
    public void userLoginSuccessfully(){
        driver.findElement(myAccount_Button).isDisplayed();
    }
    public void errorMessage(){
        Assert.assertEquals(driver.findElement(errorMessage).getText() , "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");
    }


}
