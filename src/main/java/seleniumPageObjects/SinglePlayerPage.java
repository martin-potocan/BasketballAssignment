package seleniumPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerPage {
    protected WebDriver driver;

    public SinglePlayerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table//a[@data-id=\"nba:games:game-details-box-score:video-box-score\" and contains(@href, \"ContextMeasure=FG3M\")]")
    private List<WebElement> allMadeThreePointerLinks;

    public List<Integer> getListOfMadeThreePointers() {
        List<Integer> madeThreePointers = new ArrayList<>();
        for (WebElement singleGameMadeThreePointerLinks : allMadeThreePointerLinks) {
            madeThreePointers.add(Integer.parseInt(singleGameMadeThreePointerLinks.getText()));
        }
        return madeThreePointers;
    }
}
