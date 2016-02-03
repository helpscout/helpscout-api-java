package net.helpscout.api;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.helpscout.api.json.JsonFormatter;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class AbstractApiClientTest {

    private static final int PORT = 9091;
    private static final String BASE_URL = "http://localhost:" + PORT + "/v1/";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    protected static ApiClient client = ApiClient.getInstance();

    @BeforeClass
    public static void setUpApiClient() {
        client.setBaseUrl(BASE_URL);
    }

    @SneakyThrows
    protected String getResponse(String responseFile) {
        if (responseFile == null) {
            return "";
        }
        Path responseFilePath = Paths.get(ClassLoader.getSystemResource("responses/" + responseFile + ".json").toURI());
        return new String(Files.readAllBytes(responseFilePath), "UTF-8");
    }

    protected void stubGET(String url, String responseName) {
        givenThat(get(urlEqualTo(url))
                .willReturn(aResponse().withStatus(HTTP_OK)
                        .withBody(getResponse(responseName))));
    }


    @AllArgsConstructor
    class ApiExceptionMatcher extends TypeSafeMatcher<ApiException> {

        private final String details;

        @SneakyThrows
        @Override
        protected boolean matchesSafely(ApiException e) {
            return (details == null && e.getDetails() == null)
                    || (details != null && e.getDetails() != null && JSONCompare.compareJSON(details, e.getDetails(), JSONCompareMode.LENIENT).passed());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("exception details ").appendValue(details);
        }

        @Override
        protected void describeMismatchSafely(ApiException item, Description mismatchDescription) {
            mismatchDescription.appendText("were ").appendValue(item.getDetails());
        }
    }

}
