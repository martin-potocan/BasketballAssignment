import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PlayerClient {

    private static final String PLAYERS_URL =
            "https://api.sportsdata.io/v3/nba/scores/json/PlayersBasic/LAL?key=";
    private final String apiKey;
    private final HttpClient client;

    public PlayerClient() {
        this.client = HttpClient.newHttpClient();
        this.apiKey = System.getenv("SPORTSDATAIO_API_KEY");

        if (this.apiKey == null || this.apiKey.isBlank()) {
            throw new IllegalStateException("Missing environment variable: API_KEY");
        }
    }

    public List<BasicPlayerData> fetchPlayers() throws IOException, InterruptedException {
        HttpResponse<String> response;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PLAYERS_URL + apiKey))
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Unexpected response code: " + response.statusCode()
                    + " body: " + response.body());
        }

        String json = response.body();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() {
        });
    }
}
