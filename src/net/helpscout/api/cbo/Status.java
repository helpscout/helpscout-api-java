package net.helpscout.api.cbo;

public enum Status {
	NoChange("nochange", 0),
	Active  ("active", 1),
	Pending ("pending", 2),
	Closed  ("closed", 3),
	Spam    ("spam", 4);

	private final int value;	
	private final String key;

	private Status(String key, int value) {
		this.value = value;		
		this.key = key;
	}

	public int getValue() {
		return this.value;
	}
	
	public String getKey() {
		return this.key;
	}

	
	public static Status findByValue(Integer value) {
		for (Status item : Status.values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public static Status findByKey(String key) {
		for (Status item : Status.values()) {
			if (item.getKey().equalsIgnoreCase(key)) {
				return item;
			}
		}
		return null;
	}
}