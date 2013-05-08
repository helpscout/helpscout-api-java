package net.helpscout.api.cbo;

/**
 * WorkflowStatus -
 *
 * @author briandame@gmail.com
 */
public enum WorkflowStatus {

    Active("active", 0),
    Inactive("inactive", 1),
    Invalid("invalid", 2),
    Deleted("deleted", 3);

    private final String key;
    private final int value;


    private WorkflowStatus(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    public static WorkflowStatus findByValue(int value) {
        for (WorkflowStatus item : WorkflowStatus.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public static WorkflowStatus findByKey(String key) {
        for (WorkflowStatus item : WorkflowStatus.values()) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }
}
