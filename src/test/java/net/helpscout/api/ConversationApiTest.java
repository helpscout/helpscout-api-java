package net.helpscout.api;

import lombok.SneakyThrows;
import lombok.val;
import net.helpscout.api.cbo.PersonType;
import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.customfield.*;
import net.helpscout.api.model.thread.AbstractThread;
import org.hamcrest.Matcher;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.google.common.collect.ImmutableList.of;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;

public class ConversationApiTest extends AbstractApiClientTest {

    @Test
    @SneakyThrows
    public void shouldReturnCustomFields() {
        stubGET("/v1/conversations/10.json", "conversation_10");

        Conversation conversation = client.getConversation(10L);

        List<SingleLineCustomFieldResponse> responses = of(
                singleLineCustomField(10L, "Project Name", "Project 1"),
                singleLineCustomField(11L, "Purchase Order", "")
        );

        assertThat(conversation.getCustomFields(), (Matcher) equalTo(responses));
    }

    @Test
    @SneakyThrows
    public void shouldParseConversation_WhenCustomFieldsAreNotPresent() {
        stubGET("/v1/conversations/11.json", "conversation_11");

        Conversation conversation = client.getConversation(11L);
        assertNull(conversation.getCustomFields());
    }

    @Test
    @SneakyThrows
    public void shouldParseCcFieldsInConversation() {
        stubGET("/v1/conversations/11.json", "conversation_11");

        Conversation conversation = client.getConversation(11L);

        assertThat(conversation.getCcList(), hasSize(2));
    }

    @Test
    @SneakyThrows
    public void shouldParseBccFieldsInConversation() {
        stubGET("/v1/conversations/11.json", "conversation_11");

        Conversation conversation = client.getConversation(11L);

        assertThat(conversation.getBccList(), hasSize(1));
    }

    @Test
    @SneakyThrows
    public void shouldParseCcFieldsInThread() {
        stubGET("/v1/conversations/11.json", "conversation_11");

        Conversation conversation = client.getConversation(11L);
        AbstractThread thread = getFirstThread(conversation);

        assertThat(thread.getCcList(), hasSize(1));
    }

    @Test
    @SneakyThrows
    public void shouldParseToFieldsInThread() {
        stubGET("/v1/conversations/11.json", "conversation_11");

        Conversation conversation = client.getConversation(11L);
        AbstractThread thread = getFirstThread(conversation);

        assertThat(thread.getToList(), hasSize(2));
    }

    @Test
    @SneakyThrows
    public void shouldParseBccFieldsInThread() {
        stubGET("/v1/conversations/11.json", "conversation_11");

        Conversation conversation = client.getConversation(11L);
        AbstractThread thread = getFirstThread(conversation);

        assertThat(thread.getBccList(), hasSize(2));
    }

    @Test
    @SneakyThrows
    public void shouldUpdateConversationsWithCustomFields() {
        givenThat(put(urlEqualTo("/v1/conversations/12.json"))
                .willReturn(aResponse().withStatus(HTTP_OK)));

        Conversation conversation = new Conversation();
        conversation.setId(12L);
        conversation.setCustomFields(of(
                singleLineCustomField(10L, null, "My project")
        ));
        client.updateConversation(conversation);

        verify(putRequestedFor(urlEqualTo("/v1/conversations/12.json"))
                .withRequestBody(equalToJson("{\"id\":12,\"isDraft\":false,\"threadCount\":0,\"customFields\":[{\"fieldId\":10,\"value\":\"My project\"}]}")));
    }

    @Test
    @SneakyThrows
    public void shouldCreateConversation() {
        givenThat(post(urlEqualTo("/v1/conversations.json"))
                .willReturn(aResponse().withStatus(HTTP_CREATED)));

        Conversation conversation = new Conversation();
        conversation.setCustomFields(of(
                singleLineCustomField(10L, null, "My project")
        ));
        client.createConversation(conversation);

        verify(postRequestedFor(urlEqualTo("/v1/conversations.json"))
                .withRequestBody(equalToJson("{\"isDraft\":false,\"threadCount\":0,\"customFields\":[{\"fieldId\":10,\"value\":\"My project\"}]}")));

    }

    @Test
    @SneakyThrows
    public void shouldProperlyParseAllCustomFieldTypes() {
        stubGET("/v1/conversations/13.json", "conversation_13");

        Conversation conversation = client.getConversation(13L);
        val fields = conversation.getCustomFields();

        assertTypeAndValue(fields.get(0), SingleLineCustomFieldResponse.class, "Project 1");
        assertTypeAndValue(fields.get(1), MultiLineCustomFieldResponse.class, "Hey");
        assertTypeAndValue(fields.get(2), NumberCustomFieldResponse.class, 5);
        assertTypeAndValue(fields.get(3), DateCustomFieldResponse.class, LocalDate.parse("2015-01-02").toDate());
        assertTypeAndValue(fields.get(4), DropDownCustomFieldResponse.class, 4L);
    }

    @Test
    @SneakyThrows
    public void shouldProperlyParseAllCustomFieldProperties() {
        stubGET("/v1/conversations/13.json", "conversation_13");

        Conversation conversation = client.getConversation(13L);
        val fields = conversation.getCustomFields();
        val firstField = fields.get(0);

        assertThat(firstField.getFieldId(), equalTo(10L));
        assertThat(firstField.getName(), equalTo("Project Name"));
        assertThat(firstField.getValue(), (Matcher) equalTo("Project 1"));
    }

    @Test
    @SneakyThrows
    public void shouldSetTeamPersonType() {
        stubGET("/v1/conversations/10.json", "conversation_10");

        givenThat(get(urlEqualTo("/v1/conversations/10.json"))
                .willReturn(aResponse().withStatus(HTTP_OK)
                        .withBody(getResponse("conversation_10"))));

        Conversation conversation = client.getConversation(10L);
        assertThat(conversation.getOwner().getType(), equalTo(PersonType.Team));
    }

    private SingleLineCustomFieldResponse singleLineCustomField(Long id, String name, String value) {
        val field = new SingleLineCustomFieldResponse();
        field.setName(name);
        field.setFieldId(id);
        field.setValue(value);
        return field;
    }

    private static void assertTypeAndValue(CustomFieldResponse<?> customFieldResponse, Class<? extends CustomFieldResponse<?>> fieldClass, Object fieldValue) {
        assertThat(customFieldResponse, instanceOf(fieldClass));
        val value = customFieldResponse.getValue();
        assertThat(value, equalTo(fieldValue));
    }

    private static AbstractThread getFirstThread(Conversation conversation) {
        return (AbstractThread) conversation.getThreads().get(0);
    }

}
