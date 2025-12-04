import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumPageObjects.AllPlayersPage;
import seleniumPageObjects.SinglePlayerPage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AverageThreePointersTest extends CoreTest {

    // the number of last games we are looking at
    private static final Integer LAST_GAMES_NUM = 5;

    @DataProvider(name = "players")
    public Iterator<BasicPlayerData> retrievePlayers() throws IOException, InterruptedException {
        PlayerClient client = new PlayerClient();
        List<BasicPlayerData> players = client.fetchPlayers();
        return players.iterator();
    }

    @Test(dataProvider = "players")
    public void ThreePointerTestCase(BasicPlayerData player) {
        AllPlayersPage allPlayersPage = new AllPlayersPage(driver);
        SinglePlayerPage singlePlayer = new SinglePlayerPage(driver);

        driver.get("https://www.nba.com/players");
        allPlayersPage.closeCookieDialogButton();
        allPlayersPage.searchForPlayer(player.getFirstName(), player.getLastName());
        allPlayersPage.clickOnPlayerLink();

        List<Integer> madeThreePointers = singlePlayer.getListOfMadeThreePointers();
        double threePointAverage = madeThreePointers.stream()
                .mapToDouble(Integer::doubleValue)
                .sum() / LAST_GAMES_NUM;
        Assert.assertTrue(threePointAverage >= 1, ("%s %s had an average of only %.2f three pointers in the last %d games!")
                .formatted(player.getFirstName(), player.getLastName(), threePointAverage, LAST_GAMES_NUM));
    }
}
