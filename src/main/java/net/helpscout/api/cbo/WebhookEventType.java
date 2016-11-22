package net.helpscout.api.cbo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ivan
 * Date: 23.07.16
 * Time: 0:28
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WebhookEventType {

    Unknown(0, "unknown"),
    Assigned(1, "convo.assigned"),
    Created(2, "convo.created"),
    Deleted(3, "convo.deleted"),
    Merged(4, "convo.merged"),
    Moved(5, "convo.moved"),
    StatusUpdated(6, "convo.status"),
    TagsUpdated(7, "convo.tags"),
    CustomerReplyCreated(8, "convo.customer.reply.created"),
    AgentReplyCreated(9, "convo.agent.reply.create"),
    NoteCreated(10, "convo.note.created"),
    CustomerCreated(11, "customer.created"),
    RatingReceived(12, "satisfaction.ratings"),
    TestEvent(13, "helpscout.test");

    @Getter
    private final int value;
    @Getter
    private final String label;

    private boolean isEventTypeOf(String eventType) {
        String event = this.getLabel();
        if (event != null) {
            return event.startsWith(eventType);
        }
        return false;
    }

    public boolean isTestEvent() {
        return TestEvent.equals(findByLabel(this.getLabel()));
    }

    /**
     * Is the current event a type of conversation event
     * @return boolean
     */
    public boolean isConversationEvent() {
        return this.isEventTypeOf("convo");
    }

    /**
     * Is the current event a type of customer event
     * @return boolean
     */
    public boolean isCustomerEvent() {
        return this.isEventTypeOf("customer");
    }

    public static WebhookEventType findByValue(Integer value) {
        for (WebhookEventType item : WebhookEventType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return WebhookEventType.Unknown;
    }

    public static WebhookEventType findByLabel(String label) {
        for (WebhookEventType item : WebhookEventType.values()) {
            if (item.getLabel().equalsIgnoreCase(label)) {
                return item;
            }
        }
        return WebhookEventType.Unknown;
    }
}
