package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class P03_homePage {
    private WebDriver driver;
    WebDriverWait wait;
    public SoftAssert softAssert;

    public P03_homePage(WebDriver driver) {
        this.driver = driver;
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
        PageFactory.initElements(driver, this);
    }

    By searchBar = By.id("small-searchterms");
    By searchButton = By.cssSelector(".button-1.search-box-button");
    By searchResults = By.cssSelector(".search-results");
    By searchResultItem = By.cssSelector(".product-title");
    By skuItemResult = By.cssSelector(".additional-details");
    By skuItemResultValue = By.cssSelector(".value");
    By dropDownList = By.id("customerCurrency");
    By Euro = By.cssSelector("[value=\"https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2F\"]");
    By productPrices = By.cssSelector(".price.actual-price");
    By TopMenu = By.xpath("/html/body/div[6]/div[2]/ul[1]/li");
    String mainWindowHandle;
    By Wishlist = By.className("wishlist-qty");
    By HomePageItems = By.xpath("//*[@id=\"main\"]/div/div/div/div/div[4]/div[2]/div");

    public void deepLinkToHomePage() {
        driver.navigate().to("https://demo.nopcommerce.com");
    }

    public void DropDownList() {
        driver.findElement(dropDownList).click();
    }

    public void selectEuro() {
        WebElement currencyDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("customerCurrency")));
        currencyDropdown.click();
        WebElement Euro = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[value=\"https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2F\"]")));
        Euro.click();
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(By.cssSelector(".product-price"));
    }

    public void EnterSearchWord(String product_name) {
        driver.findElement(searchBar).sendKeys(product_name);
    }

    public void ClickOnSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void ClickOnSearchResult() {
        driver.findElement(searchResultItem).click();
    }

    public void searchResultItemContainTheWord(String product_name) {
        String title = driver.getTitle().toLowerCase();
        assertTrue(title.contains(product_name.toLowerCase()), "Expected: " + product_name + ", Actual: " + title);
    }

    public void VerifySearchResultsByProductName(String product_name) {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-title"));
        for (WebElement product : products) {
            if (product.getText().contains(product_name)) {
                System.out.println("Product found: " + product.getText());
            } else {
                System.out.println("Product not found");
            }
        }

        WebElement results = driver.findElement(searchResults);
        List<WebElement> items = results.findElements(searchResultItem);

        for (WebElement item : items) {
            System.out.println(item.getText());
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            softAssert.assertTrue(driver.getCurrentUrl().contains("https://demo.nopcommerce.com/search?q="), "Expected: https://demo.nopcommerce.com/search?q=, Actual: " + driver.getCurrentUrl());
            softAssert.assertAll();
        }
    }

    public void VerifySearchResultsBySKU(String sku) {
        WebElement item = driver.findElement(skuItemResult);
        String value = item.findElement(skuItemResultValue).getText();
        assertTrue(value.toLowerCase().contains(sku.toLowerCase()), "Expected: " + sku + ", Actual: " + value);
    }

    public void clickOnSubCategory(String category, String subCategory) {
        List<WebElement> categories = driver.findElements(TopMenu);
        Actions action = new Actions(driver);

        for (WebElement currentCategory : categories) {
            String categoryName = currentCategory.getText().trim().toLowerCase();

            if (categoryName.contains(category.toLowerCase().trim())) {
                action.moveToElement(currentCategory).perform();

                List<WebElement> subCategories = currentCategory.findElements(By.xpath(".//ul/li"));

                for (WebElement sub : subCategories) {
                    String subCategoryName = sub.getText().trim().toLowerCase();

                    if (subCategoryName.contains(subCategory.toLowerCase().trim())) {
                        sub.findElement(By.tagName("a")).click();
                        return;
                    }
                }

                // If no matching subcategory is found, click the main category
                currentCategory.click();
                return;
            }
        }

        System.out.println("Category or Subcategory not found");
    }


    public void VerifySubCategoryMatchItsPage(String SubCategory) {
        System.out.println("Current URL: " + driver.getCurrentUrl());
        String pageHeader = driver.findElement(By.cssSelector("#main > div > div.center-2 > div > div.page-title > h1")).getText();
        System.out.println(pageHeader);
        Assert.assertTrue(pageHeader.toLowerCase().trim().contains(SubCategory.toLowerCase().trim()), "Expected: " + SubCategory + ", Actual: " + pageHeader);
    }

    public void clickOnSliderItem(String item, String itemNumber) {
        try {
            Thread.sleep(2000); // Wait for 2 seconds to ensure the slider items are loaded

            List<WebElement> sliderItems = driver.findElements(By.className("nivo-imageLink"));
            int index = Integer.parseInt(itemNumber) - 1;

            // Check if the specified item is displayed
            if (sliderItems.get(index).getCssValue("display").contains("block")) {
                try {
                    sliderItems.get(index).click(); // Click on the specified item
                    Thread.sleep(2000); // Wait for 2 seconds after clicking
                } catch (Exception e) {
                    System.out.println("Could not click on item: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Got an error: " + e.getMessage());
        }
    }


    public void VerifySliderItemURL(String URL) {
        System.out.println("Current URL: " + driver.getCurrentUrl());
        Assert.assertEquals(URL.toLowerCase().trim(), driver.getCurrentUrl().toLowerCase().trim(), "Expected: " + URL + ", Actual: " + driver.getCurrentUrl());
    }

    public void clickOnChannel(String channel) {
        try {
            WebElement link = driver.findElement(By.className(channel));
            System.out.println(link.getText());
            mainWindowHandle = driver.getWindowHandle();
            link.click();
            Thread.sleep(4000); // Wait for 4 seconds to ensure the new window is fully loaded
        } catch (Exception e) {
            System.out.println("Got an error: " + e.getMessage());
        }
    }

    public void verifyChannelLinks(String link) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        if (allWindowHandles.size() == 1) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(link.toLowerCase().trim(), currentUrl.toLowerCase().trim(), "Expected: " + link + ", Actual: " + currentUrl);
        } else {
            for (String windowHandle : allWindowHandles) {
                if (!windowHandle.equals(mainWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("Switched to new window: " + windowHandle);
                    String currentWindowUrl = driver.getCurrentUrl();
                    System.out.println("Current URL: " + currentWindowUrl);
                    driver.close();
                    driver.switchTo().window(mainWindowHandle);
                    Assert.assertEquals(link.toLowerCase().trim(), currentWindowUrl.toLowerCase().trim(), "Expected: " + link + ", Actual: " + currentWindowUrl);
                    break;
                }
            }
        }
    }

    public void clickOnLoveIconOfSpecificProduct(String product) {
        List<WebElement> items = driver.findElements(HomePageItems);
        System.out.println(items.size());

        for (int i = 0; i < items.size(); i++) {
            WebElement item = items.get(i);
            String productName = item.findElement(By.className("product-title")).getText().trim().toLowerCase();

            if (productName.equals(product.trim().toLowerCase())) {
                String xpath = "//*[@id=\"main\"]/div/div/div/div/div[4]/div[2]/div[" + (i + 1) + "]/div/div[2]/div[3]/div[2]/button[3]";
                System.out.println(xpath);
                item.findElement(By.xpath(xpath)).click();
                break;
            }
        }
    }

    public void verifyDisplayASuccessfulMessageWhenClickOnAddToWishList() {
        try {
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println(currentUrl);

            if (!currentUrl.equals("https://demo.nopcommerce.com/")) {
                Assert.assertEquals(currentUrl, "https://demo.nopcommerce.com/", "Expected: https://demo.nopcommerce.com/, Actual: " + currentUrl);
                driver.navigate().to("https://demo.nopcommerce.com/");
            } else {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
                WebElement notificationDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bar-notification")));
                String successMessageText = notificationDiv.getText();
                System.out.println(successMessageText);
                WebElement successMessageBgColorElement = notificationDiv.findElement(By.tagName("div"));
                softAssert.assertTrue(successMessageBgColorElement.isDisplayed(), "Expected: True, Actual: " + successMessageBgColorElement.isDisplayed());
                String successMessageBgColor = Color.fromString(successMessageBgColorElement.getCssValue("background-color")).asHex();
                System.out.println(successMessageBgColor);
                softAssert.assertTrue(successMessageText.contains("The product has been added to your wishlist"), "Expected: The product has been added to your wishlist, Actual: " + successMessageText);
                softAssert.assertEquals(successMessageBgColor, "#4bb07a", "Expected: #4bb07a, Actual: " + successMessageBgColor);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyTheWishlistCounter() {
        try {
            Thread.sleep(3000);
            WebElement wishlist = driver.findElement(Wishlist);
            String wishlistCounter = wishlist.getText().replace("(", "").replace(")", "");
            Assert.assertTrue(Integer.parseInt(wishlistCounter) > 0, "Expected: More than 0, Actual: " + wishlistCounter);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void searchResultItemContainTheWorrd() {

    }
}
