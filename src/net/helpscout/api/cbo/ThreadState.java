package net.helpscout.api.cbo;

public enum ThreadState {
	Published   ("published", 1),
	Draft       ("draft", 2),
	UnderReview ("underreview", 3);	

	private final int value;	
	private final String key;

	private ThreadState(String key, int value) {
		this.value = value;		
		this.key = key;
	}

	public int getValue() {
		return this.value;
	}

	public String getKey() {
		return this.key;
	}

	public static ThreadState findByValue(Integer value) {
		for (ThreadState item : ThreadState.values()) {
			if (item.getValue() == value) {				
				return item;
			}
		}
		return null;
	}

	public static ThreadState findByKey(String key) {
		for (ThreadState item : ThreadState.values()) {
			if (item.getKey().equalsIgnoreCase(key)) {
				return item;
			}
		}
		return null;
	}
}