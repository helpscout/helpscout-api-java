package net.helpscout.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.SneakyThrows;
import lombok.val;
import net.helpscout.api.exception.InvalidFormatException;
import net.helpscout.api.json.JsonFormatter;
import net.helpscout.api.model.Customer;

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
            .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST).withBody(error)));
        
        try {
            client.getMailboxes();
        } catch (ApiException e) {
            assertThat(e.getDetails(), is(formatted(error)));
        }
    }
    
	@Test
	public void shouldRetrieveSpecificCustomer() throws ApiException {
		givenThat(get(urlEqualTo("/v1/customers/60984612.json"))
				.willReturn(aResponse().withStatus(HTTP_OK)
				.withBody(getResponse("customer"))));

		Long customerId = 60984612L;
		Customer customer = client.getCustomer(customerId);
		assertEquals("Peter", customer.getFirstName());
		assertEquals(customerId, customer.getId());
		assertNotNull(customer.getAddress().getCreatedAt());
		Long addressId = 1187643L;
		assertEquals(addressId, customer.getAddress().getId());

	}

	@SneakyThrows
	private String getResponse(String responseFile) {
		if (responseFile == null) {
			return "";
		}
		String response = null;
		Path responseFilePath = Paths.get(ClassLoader.getSystemResource("responses/" + responseFile + ".json").toURI());
		response = new String(Files.readAllBytes(responseFilePath));
		return response;
	}
    
    private String formatted(String rawJson) {
        return new JsonFormatter().format(rawJson);
    }
}