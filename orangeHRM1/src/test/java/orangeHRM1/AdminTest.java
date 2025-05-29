package orangeHRM1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class AdminTest {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginBtn = By.tagName("button");
    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    By adminMenu = By.xpath("//span[text()='Admin']");
    By systemUsersHeader = By.xpath("//h5[text()='System Users']");
    By searchUsernameField = By.xpath("//label[text()='Username']/../following-sibling::div//input");
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By userResult = By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'Admin')]");

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys("admin123");
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

        // Wait for Dashboard
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        System.out.println("Login successful.");
    }

    @Test
    public void testSearchUserInAdminPanel() {
        // Navigate to Admin page
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersHeader));

        // Search for "Admin" user
        WebElement searchUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsernameField));
        searchUsername.clear();
        searchUsername.sendKeys("Admin");

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        // Verify at least one result found with text "Admin"
        List<WebElement> users = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(userResult));
        Assert.assertTrue(users.size() > 0, "No user found with username 'Admin'");

        System.out.println("User 'Admin' found in Admin panel.");
    }

//    @AfterClass
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//            System.out.println("Browser closed.");
//       }
//    }
}
