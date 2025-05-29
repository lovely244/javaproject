package orangeHRM1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PersonalDetailsTest {

    @Test
    public void testViewPersonalDetails() {
        // Set the path to your ChromeDriver
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\lovely\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Launch Chrome
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open OrangeHRM demo site
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Login to the application
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("admin123");

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
        loginBtn.click();


        // Wait for Dashboard to appear
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));

        // Navigate to My Info tab
        WebElement myInfo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/pim/viewMyDetails']")));
        myInfo.click();

        // Wait for Personal Details section
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));

        // Check if first name field is displayed
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
        Assert.assertTrue(firstNameField.isDisplayed(), "First Name field should be displayed");

        // Check if last name field is displayed
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
        Assert.assertTrue(lastNameField.isDisplayed(), "Last Name field should be displayed");

        System.out.println("✅ Personal Details page is displayed with First and Last Name fields visible.");

        // Close the browser
        driver.quit();
    }
}
