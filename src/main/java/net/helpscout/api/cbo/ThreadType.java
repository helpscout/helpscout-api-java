package net.helpscout.api.cbo;

/**
 * ThreadType -
 *
 * @author briandame@gmail.com
 */
public enum ThreadType {

	LineItem(0, "lineitem"),
	Note(1, "note"),
	Message(2, "message"),
	Customer(3, "customer"),
	ForwardParent(4, "forwardparent"),
	ForwardChild(5, "forwardchild"),
	Chat(6, "chat"),
	Phone(7, "phone");

	private final int value;
	private final String label;

	private ThreadType(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

	public static ThreadType findByValue(Integer value) {
		for (ThreadType item : ThreadType.values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public static ThreadType findByLabel(String label) {
		for (ThreadType item : ThreadType.values()) {
			if (item.getLabel().equalsIgnoreCase(label)) {
				return item;
			}
		}
		return null;
	}
}
