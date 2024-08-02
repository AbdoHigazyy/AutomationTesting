package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class P01_register {
    private WebDriver driver ;
    public P01_register(WebDriver driver){
        this.driver = driver ;
    }
    //locators
    By male_RadioButton = By.id("gender-male");
    By firstname_TextBox = By.id("FirstName");
    By lastname_TextBox = By.id("LastName");
    By DayInput = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[1]");
    By MonthInput = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[2]");
    By YearInput = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div/select[3]");
    By email_TextBox = By.id("Email");
    By password_TextBox = By.id("Password");
    By confirmpassword_TextBox = By.id("ConfirmPassword");
    By register_Button = By.id("register-button");
    By succesMessage_Label = By.className("result");
    Select dropd;



    //actions
    public void deepLinkToRegisterPage()
    {
        driver.navigate().to("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }
public void selectMaleGender()
{
    driver.findElement(male_RadioButton).click();
}
public void typeFirstAndLastName(String firstname , String lastname)
{
driver.findElement(firstname_TextBox).sendKeys(firstname);
driver.findElement(lastname_TextBox).sendKeys(lastname);
}
    public void EnterDate(String Day, String Month, String Year) {
        dropd = new Select(driver.findElement(DayInput));
        dropd.selectByVisibleText(Day);
        dropd = new Select(driver.findElement(MonthInput));
        dropd.selectByVisibleText(Month);
        dropd = new Select(driver.findElement(YearInput));
        dropd.selectByVisibleText(Year);
    }

    public void EnterDay(String Day) {
        dropd = new Select(driver.findElement(DayInput));
        dropd.selectByVisibleText(Day);
    }

    public void EnterMonth(String Month) {
        dropd = new Select(driver.findElement(MonthInput));
        dropd.selectByVisibleText(Month);
    }

    public void EnterYear(String Year) {
        dropd = new Select(driver.findElement(YearInput));
        dropd.selectByVisibleText(Year);
    }
public void typeEmail(String email)
{
    driver.findElement(email_TextBox).sendKeys(email);
}
public void fillPasswordFields(String password, String confirmpassword)
{
    driver.findElement(password_TextBox).sendKeys(password);
    driver.findElement(confirmpassword_TextBox).sendKeys(confirmpassword);
}
public void clickRegisterButton()
{
    driver.findElement(register_Button).click();
}
public void validateSuccessfulRegisterationProccess()
{
    Assert.assertEquals(driver.findElement(succesMessage_Label).getText() , "Your registration completed");
}


}
