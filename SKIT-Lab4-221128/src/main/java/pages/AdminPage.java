//package pages;// package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//// ----------- AdminPage.java --------------
//public class AdminPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    // Зелено Add копче за отворање форма за додавање корисник
//    private By addButton = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
//
//    // Полња од формата за додавање корисник
//    private By userRoleDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div");
//    private By selectAdminRole = By.xpath("//div[@role='listbox']//span[text()='Admin']");
//    private By employeeNameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
//    private By employeeNameOption = By.xpath("//div[@role='option'][1]");
//    private By statusDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div");
//    private By selectEnabled = By.xpath("//div[@role='listbox']//span[text()='Enabled']");
//    private By usernameInput = By.xpath("//label[text()='Username']/following-sibling::input");
//    private By passwordInput = By.xpath("//label[text()='Password']/following-sibling::input");
//    private By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/following-sibling::input");
//    private By saveButton = By.xpath("//button[@type='submit']");
//
//    // Листа со корисници
//    private By userRows = By.xpath("//div[@class='oxd-table-body']//div[@role='row']");
//    private By searchInput = By.xpath("//input[@placeholder='Search']");
//
//    // Delete копче и потврда
//    private By deleteButton = By.xpath("//button[contains(@class,'oxd-button--label-danger')]");
//    private By confirmDeleteButton = By.xpath("//button/span[text()='Yes, Delete']");
//
//    public AdminPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void clickAddUser() {
//        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
//    }
//
//    public void addUser(String role, String employeeName, String username, String password) {
//        // Кликни на custom dropdown за User Role
//        WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.oxd-select-text-input")));
//        userRoleDropdown.click();
//
//        // Почекај додека опциите не се појават
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
//
//        // Наоѓа сите опции
//        List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
//
//        // Кликни на опцијата со текст што ја бараме
//        boolean found = false;
//        for (WebElement option : options) {
//            if (option.getText().trim().equals(role)) {
//                option.click();
//                found = true;
//                break;
//            }
//        }
//        if (!found) {
//            throw new NoSuchElementException("Role option '" + role + "' not found in dropdown");
//        }
//
//        // Пополнување на полето за вработен
//        WebElement empNameField = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
//        empNameField.clear();
//        empNameField.sendKeys(employeeName);
//
//
//        // Пополнување на корисничко име и лозинка
//        driver.findElement(By.xpath("//label[text()='Username']/following::input[1]")).sendKeys(username);
//        driver.findElement(By.xpath("//label[text()='Password']/following::input[1]")).sendKeys(password);
//        driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input[1]")).sendKeys(password);
//
//        // Кликни на submit
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//    }
//
//
//    public void deleteUser(String username) {
//        // Внеси го корисничкото име во полето за пребарување
//        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Username']/following::input[1]")));
//        searchField.clear();
//        searchField.sendKeys(username);
//
//        // Кликни на Search копчето
//        WebElement searchButton = driver.findElement(By.xpath("//button[text()=' Search ']"));
//        searchButton.click();
//
//        // Почекај додека се прикаже резултатот со корисникот, т.е. редот со username-то
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='" + username + "']")));
//        } catch (TimeoutException e) {
//            throw new NoSuchElementException("User with username '" + username + "' not found");
//        }
//
//        // Кликни на иконата за бришење од редот каде што е username-от
//        WebElement deleteButton = driver.findElement(By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='" + username + "']/ancestor::div[contains(@class,'oxd-table-row')]//button[contains(@class,'oxd-icon-button') and .//i[contains(@class,'bi-trash')]]"));
//        deleteButton.click();
//
//        // Почекај и кликни Yes за да потврдиш бришење
//        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Yes, Delete ']")));
//        confirmDelete.click();
//    }
//}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // селектори - приватни променливи
    private By addButton = By.xpath("//button[text()=' Add ']");

    private By userRoleDropdown = By.cssSelector("div.oxd-select-text-input");
    private By userRoleOptions = By.xpath("//div[@role='option']");

    private By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");



    private By usernameInput = By.xpath("//label[text()='Username']/following::input[1]");
    private By passwordInput = By.xpath("//label[text()='Password']/following::input[1]");
    private By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/following::input[1]");

    private By submitButton = By.xpath("//button[@type='submit']");

    private By searchInput = By.xpath("//label[text()='Username']/following::input[1]");
    private By searchButton = By.xpath("//button[text()=' Search ']");

    private By userRowByUsername(String username) {
        return By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='" + username + "']/ancestor::div[contains(@class,'oxd-table-row')]");
    }

    private By deleteButtonByUsername(String username) {
        return By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='" + username + "']/ancestor::div[contains(@class,'oxd-table-row')]//button[contains(@class,'oxd-icon-button') and .//i[contains(@class,'bi-trash')]]");
    }

    private By confirmDeleteButton = By.xpath("//button[text()=' Yes, Delete ']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void addUser(String role, String employeeName, String username, String password, String confirmPassword, String status) {
        // Кликни на User Role dropdown

        List<WebElement> dropdowns = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("div.oxd-select-text-input"), 1
        ));
        WebElement userRoleDropdown = dropdowns.get(0);
        userRoleDropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
        for (WebElement option : driver.findElements(By.xpath("//div[@role='option']"))) {
            if (option.getText().trim().equals(role)) {
                option.click();
                break;
            }
        }

        WebElement statusDropdown = dropdowns.get(1);
        statusDropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
        for (WebElement option : driver.findElements(By.xpath("//div[@role='option']"))) {
            if (option.getText().trim().equals(status)) {
                option.click();
                break;
            }
        }

        // Внеси Employee Name
        WebElement empNameField = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        empNameField.clear();
        empNameField.sendKeys(employeeName);

        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + employeeName + "']")
        ));
        suggestion.click();


        // Внеси Username
        driver.findElement(usernameInput).sendKeys(username);
        // Внеси Password
        driver.findElement(passwordInput).sendKeys(password);
        // Внеси Confirm Password
        driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);

        // Кликни Submit
        driver.findElement(submitButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-toast--success")));
    }



    public void addInvalidUser(String role, String employeeName, String username, String password, String confirmPassword, String status) {
        // Кликни на User Role dropdown

        List<WebElement> dropdowns = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("div.oxd-select-text-input"), 1
        ));
        WebElement userRoleDropdown = dropdowns.get(0);
        userRoleDropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
        for (WebElement option : driver.findElements(By.xpath("//div[@role='option']"))) {
            if (option.getText().trim().equals(role)) {
                option.click();
                break;
            }
        }

        WebElement statusDropdown = dropdowns.get(1);
        statusDropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
        for (WebElement option : driver.findElements(By.xpath("//div[@role='option']"))) {
            if (option.getText().trim().equals(status)) {
                option.click();
                break;
            }
        }

        // Внеси Employee Name
        WebElement empNameField = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        empNameField.clear();
        empNameField.sendKeys(employeeName);

        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + employeeName + "']")
        ));
        suggestion.click();


        // Внеси Username
        driver.findElement(usernameInput).sendKeys(username);
        // Внеси Password
        driver.findElement(passwordInput).sendKeys(password);
        // Внеси Confirm Password
         driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);


    }





    public void deleteUser(String username) throws InterruptedException {
        // Внеси Username во Search поле
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        search.clear();
        search.sendKeys(username);

        // Кликни Search копче
        driver.findElement(searchButton).click();

        // Почекај додека корисникот се појави
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userRowByUsername(username)));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("User with username '" + username + "' not found");
        }

        // Кликни Delete копче за соодветниот корисник
        driver.findElement(deleteButtonByUsername(username)).click();

        // Потврди бришење
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-toast--success")));
    }
}
