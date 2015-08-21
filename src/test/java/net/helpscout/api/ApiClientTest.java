package net.helpscout.api;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import lombok.val;
import net.helpscout.api.exception.InvalidFormatException;
import net.helpscout.api.json.JsonFormatter;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class ApiClientTest {
    
    private static final int PORT = 8080;
    private static final String BASE_URL = "http://localhost:" + PORT + "/v1/";
    
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    private static ApiClient client = ApiClient.getInstance();
    
    @BeforeClass
    public static void setUpApiClient() {
        client.setBaseUrl(BASE_URL);
    }
    
    @Test(expected = InvalidFormatException.class)
    public void shouldThrowInvalidFormatException() throws ApiException {
        givenThat(get(urlEqualTo("/v1/mailboxes.json"))
                .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST)));

        client.getMailboxes();
    }

    @Test
    public void shouldNotReturnDetailedErrorMessageForBadRequest() {
        givenThat(get(urlEqualTo("/v1/mailboxes.json"))
                .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST)));

        try {
            client.getMailboxes();
        } catch(ApiException e) {
            assertNull(e.getDetails());
        }
    }
    
    @Test
    public void shouldReturnDetailedErrorMessageForBadRequest() {
        val error = "{\"message\":\"Invalid request.\"}";
        
        givenThat(get(urlEqualTo("/v1/mailboxes.json"))
            .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST)
                                   .withBody(error)));
        
        try {
            client.getMailboxes();
        } catch (ApiException e) {
            assertThat(e.getDetails(), is(formatted(error)));
        }
    }
    
    private String formatted(String rawJson) {
        return new JsonFormatter().format(rawJson);
    }
}