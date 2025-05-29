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

public class ContactDetailsTest {

    @Test
    public void testViewAndEditContactDetails() {
        // Set path to your ChromeDriver
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\lovely\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Launch Chrome
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open OrangeHRM
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Login
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait for dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));

        // Click on My Info
        WebElement myInfo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Info']")));
        myInfo.click();

        // Click on Contact Details in the side menu
        WebElement contactDetails = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Details']")));
        contactDetails.click();

        // Wait for page to load
        WebElement contactHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Contact Details']")));
        Assert.assertEquals(contactHeading.getText(), "Contact Details", "Contact Details heading should be visible");

        System.out.println("✅ Contact Details page is displayed.");

        // Optional: Click Edit and change city field
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'orangehrm-left-space')]")));
        editButton.click();

        WebElement cityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='City']/following::input[1]")));
        cityField.clear();
        cityField.sendKeys("Chandigarh");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        System.out.println("✅ City field updated successfully.");

        // Quit
        driver.quit();
    }
}
