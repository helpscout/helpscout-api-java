package net.helpscout.api.cbo;

/**
 * WorkflowType -
 *
 * @author briandame@gmail.com
 */
public enum WorkflowType {

    Automatic("automatic", 1),
    Manual("manual", 2);

    private final String key;
    private final int value;

    private WorkflowType(String key, int value) {
        this.key = key;
        this.value = value;

    }

    public int getValue() {
        return this.value;
    }

    public String getKey() {
        return this.key;
    }

    public static WorkflowType findByValue(Integer value) {
        for (WorkflowType item : WorkflowType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public static WorkflowType findByKey(String key) {
        for (WorkflowType item : WorkflowType.values()) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }
}
