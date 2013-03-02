package net.helpscout.api.cbo;

/**
 * ActionType -
 *
 * @author briandame@gmail.com
 */
public enum ActionType {

    MovedFromMailbox(1, "movedFromMailbox"),
    Merged(2, "merged");

    private final int value;
    private final String label;

    private ActionType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    public static ActionType findByValue(Integer value) {
        for (ActionType item : ActionType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public static ActionType findByLabel(String label) {
        for (ActionType item : ActionType.values()) {
            if (item.getLabel().equalsIgnoreCase(label)) {
                return item;
            }
        }
        return null;
    }
}
