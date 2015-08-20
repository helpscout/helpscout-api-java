package net.helpscout.api.model.customer;

public class CustomerEntry {
	private Long id;
	private String value;
	private String type;
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "CustomerEntry [id=" + id + ", value=" + value + ", type=" + type + ", location=" + location + "]";
	}

}