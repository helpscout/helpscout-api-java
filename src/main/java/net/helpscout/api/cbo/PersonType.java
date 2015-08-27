package net.helpscout.api.cbo;

/**
 * PersonType -
 *
 * @author briandame@gmail.com
 */
public enum PersonType {

    User(1, "user"),
    Customer(2, "customer");

    private final int value;
    private final String label;

    private PersonType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    public static PersonType findByValue(Integer value) {
        for (PersonType item : PersonType.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return PersonType.User;
    }

    public static PersonType findByLabel(String label) {
        for (PersonType item : PersonType.values()) {
            if (item.getLabel().equalsIgnoreCase(label)) {
                return item;
            }
        }
        return PersonType.User;
    }
}
