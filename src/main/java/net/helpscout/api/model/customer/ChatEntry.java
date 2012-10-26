package net.helpscout.api.model.customer;

public class ChatEntry extends CustomerEntry {

	public static enum Type {
		Aim("aim"),
		GTalk("gtalk"),
		ICQ("icq"),
		XMMP("xmmp"),
		MSN("msn"),
		Skype("skype"),
		Yahoo("yahoo"),
		QQ("qq"),
		Other("other");

		private final String label;

		private Type(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}

		public static Type findByLabel(String label) {
			for (Type item : Type.values()) {
				if (item.getLabel().equalsIgnoreCase(label)) {
					return item;
				}
			}
			return Type.Other;
		}
	}
}