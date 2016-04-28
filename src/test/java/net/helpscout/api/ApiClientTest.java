package net.helpscout.api;

import lombok.SneakyThrows;
import lombok.val;
import net.helpscout.api.exception.InvalidFormatException;
import net.helpscout.api.model.Customer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URLEncoder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiClientTest extends AbstractApiClientTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = InvalidFormatException.class)
    public void shouldThrowInvalidFormatException() throws ApiException {
        givenThat(get(urlEqualTo("/v1/mailboxes.json"))
                .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST)));

        client.getMailboxes();
    }

    @Test
    @SneakyThrows
    public void shouldNotReturnDetailedErrorMessageForBadRequest() {
        givenThat(get(urlEqualTo("/v1/mailboxes.json"))
                .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST)));

        thrown.expect(new ApiExceptionMatcher(null));

        client.getMailboxes();
    }

    @Test
    @SneakyThrows
    public void shouldReturnDetailedErrorMessageForBadRequest() {
        val error = "{\"message\":\"Invalid request.\"}";

        givenThat(get(urlEqualTo("/v1/mailboxes.json"))
                .willReturn(aResponse().withStatus(HTTP_BAD_REQUEST)
                        .withBody(error)));


        thrown.expect(new ApiExceptionMatcher(error));

        client.getMailboxes();
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

    @Test
    @SneakyThrows
    public void checkThatSearchQueryWithSpacesDidNotProduceException() throws ApiException {
        givenThat(get(urlMatching("/v1/search/conversations.json.*"))
                .withQueryParam("query", containing(URLEncoder.encode("This is query with spaces", "UTF-8")))
                .willReturn(aResponse().withStatus(HTTP_OK)
                        .withBody("{}")));
        client.searchConversations("This is query with spaces", "", "", 0);
    }


}