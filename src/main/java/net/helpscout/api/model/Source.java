package net.helpscout.api.model;

public class Source {
	private String type;
	private String via;
	
	public String getType() {
		return type;
	}
	public String getVia() {
		return via;
	}
	
	public boolean isViaCustomer() {
		return "customer".equals(via);
	}

	@Override
	public String toString() {
		return "Source [type=" + type + ", via=" + via + "]";
	}

}
