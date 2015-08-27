package net.helpscout.api.cbo;

/**
 * ConversationType -
 *
 * @author briandame@gmail.com
 */
public enum ConversationType {

    Email(1, "email"),
    Chat(2, "chat"),
    Phone(3, "phone");

    private final int value;
    private final String label;

    private ConversationType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    public static ConversationType findByValue(Integer value) {
        for (ConversationType item : ConversationType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return ConversationType.Email;
    }

    public static ConversationType findByLabel(String label) {
        for (ConversationType item : ConversationType.values()) {
            if (item.getLabel().equalsIgnoreCase(label)) {
                return item;
            }
        }
        return ConversationType.Email;
    }
}
