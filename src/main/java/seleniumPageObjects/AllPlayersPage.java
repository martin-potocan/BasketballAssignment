package seleniumPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AllPlayersPage {
    protected WebDriver driver;
    private final WebDriverWait wait;

    public AllPlayersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type=\"text\"]")
    private WebElement searchBar;

    @FindBy(xpath = "//table[@class=\"players-list\"]//a[contains(@href, \"player\")]")
    private WebElement playerLink;

    @FindBy(xpath = "//div[@id=\"onetrust-consent-sdk\"]//button[contains(@class, \"close\")]")
    private WebElement cookieDialogButton;

    public void searchForPlayer(String firstName, String lastName) {
        searchBar.clear();
        searchBar.sendKeys(firstName + " " + lastName.replaceAll("Jr.", ""));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@class=\"players-list\"]//tr"), 2));
    }

    public void clickOnPlayerLink() {
        playerLink.click();
    }

    public void closeCookieDialogButton() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cookieDialogButton));
            cookieDialogButton.click();
        } catch (TimeoutException e) {
            // Cookie dialog didn't open
        }
    }
}
