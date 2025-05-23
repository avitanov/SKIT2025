package tests;// package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.AdminPage;

import static org.junit.Assert.*;

// ----------- UserAdminTest.java --------------
public class UserAdminTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminPage adminPage;

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

        loginPage = new LoginPage(driver);

        adminPage = new AdminPage(driver);

        loginPage.login("Admin", "admin123");
    }

    @Test
    public void addUser() {
        adminPage.clickAddUser();
        adminPage.addUser("ESS", "Qwerty Qwerty LName", "test123456", "Pass@1234", "Pass@1234",  "Enabled");
        assertTrue(driver.getPageSource().contains("Successfully Saved"));
    }

    @Test
    public void addInvalidUser() throws InterruptedException {
        adminPage.clickAddUser();
        adminPage.addInvalidUser("ESS", "Timothy Lewis Amiano", "ddd", "Pass!1234", "Pass!1234",  "Enabled");
        WebElement errorSpan = driver.findElement(By.className("oxd-input-field-error-message"));
        assertTrue(errorSpan.getText().contains("Should be at least 5 characters"));
    }


    @Test
    public void deleteUser() throws InterruptedException {
        adminPage.deleteUser("test123456");
        assertTrue(driver.getPageSource().contains("Successfully Deleted"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
