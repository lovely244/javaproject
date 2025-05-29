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

public class LeaveTest {

    @Test
    public void testViewLeaveList() {
        // Set path to your ChromeDriver
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\lovely\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to OrangeHRM
        driver.get("https://opensource-demo.orangehrmlive.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait and fill username
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys("Admin");

        // Wait and fill password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordInput.sendKeys("admin123");

        // Wait and click login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();

        // Wait for dashboard to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));

        // Click on Leave menu
        WebElement leaveMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leave']")));
        leaveMenu.click();

        // Wait for Leave page filters (indicating page loaded)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From Date']")));

        // Verify Leave List heading
        WebElement leaveListHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Leave List']")));
        Assert.assertEquals(leaveListHeading.getText(), "Leave List", "Leave List page heading should match");

        System.out.println("✅ Leave List page is displayed correctly.");

        // Close browser
        driver.quit();
    }
}
