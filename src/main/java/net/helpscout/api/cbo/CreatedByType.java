package net.helpscout.api.cbo;

/**
 * CreatedByType -
 *
 * @author briandame@gmail.com
 */
public enum CreatedByType {

	User(1, "user"),
	Customer(2, "customer");

	private final int value;
	private final String label;

	private CreatedByType(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

	public static CreatedByType findByValue(Integer value) {
		for (CreatedByType item : CreatedByType.values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return CreatedByType.User;
	}

	public static CreatedByType findByLabel(String label) {
		for (CreatedByType item : CreatedByType.values()) {
			if (item.getLabel().equalsIgnoreCase(label)) {
				return item;
			}
		}
		return CreatedByType.User;
	}
}
