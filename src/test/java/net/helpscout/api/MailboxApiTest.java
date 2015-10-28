package net.helpscout.api;

import lombok.SneakyThrows;
import net.helpscout.api.model.customfield.CustomField;
import net.helpscout.api.model.customfield.CustomFieldOption;
import net.helpscout.api.model.customfield.CustomFieldType;
import net.helpscout.api.model.Mailbox;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.google.common.collect.ImmutableList.of;
import static java.net.HttpURLConnection.HTTP_OK;
import static net.helpscout.api.model.customfield.CustomFieldType.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MailboxApiTest extends AbstractApiClientTest {

    @Test
    @SneakyThrows
    public void shouldReturnCustomFields_WhenDefined() {
        givenThat(get(urlEqualTo("/v1/mailboxes/5.json"))
                .willReturn(aResponse().withStatus(HTTP_OK)
                        .withBody(getResponse("mailbox_5"))));

        Mailbox mailbox = client.getMailbox(5L);

        List<CustomField> customFields = of(
                fieldBuilder().fieldName("Favourite Color").id(10L).fieldType(CustomFieldType.DROPDOWN).order(1)
                        .options(of(
                                optionBuilder().id(16L).label("Blue").order(2).build(),
                                optionBuilder().id(15L).label("Red").order(1).build(),
                                optionBuilder().id(17L).label("White").order(3).build()
                        )).build(),
                fieldBuilder().fieldName("Purchase Order").id(11L).fieldType(SINGLE_LINE).order(2)
                        .options(emptyOptions()).build(),
                fieldBuilder().fieldName("Customer Notes").id(12L).fieldType(MULTI_LINE).order(3)
                        .options(emptyOptions()).build(),
                fieldBuilder().fieldName("Number Requested").id(13L).fieldType(NUMBER).order(4)
                        .options(emptyOptions()).build(),
                fieldBuilder().fieldName("Date Of Activity").id(14L).fieldType(DATE).order(5)
                        .options(emptyOptions()).build());

        assertThat(mailbox.getCustomFields(), equalTo(customFields));
    }

    @Test
    @SneakyThrows
    public void shouldParseMailbox_WhenCustomFieldsAreNotPresent() {
        givenThat(get(urlEqualTo("/v1/mailboxes/6.json"))
                .willReturn(aResponse().withStatus(HTTP_OK)
                        .withBody(getResponse("mailbox_6"))));

        Mailbox mailbox = client.getMailbox(6L);
        assertNull(mailbox.getCustomFields());
    }

    private CustomField.CustomFieldBuilder fieldBuilder() {
        return CustomField.builder();
    }

    private CustomFieldOption.CustomFieldOptionBuilder optionBuilder() {
        return CustomFieldOption.builder();
    }

    private static List<CustomFieldOption> emptyOptions() {
        return Collections.emptyList();
    }

}
