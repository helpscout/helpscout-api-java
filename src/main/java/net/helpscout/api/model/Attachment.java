package net.helpscout.api.model;

public class Attachment {
	private Long id;
	private String mimeType;
	private String fileName;
	private int size;
	private int width;
	private int height;
	private String url;
	private String hash;
	private String data;

	public boolean isImage() {
		return mimeType != null && mimeType.startsWith("image");
	}

	public Long getId() {
		return id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getFilename() {
		return fileName;
	}

	public int getSize() {
		return size;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getUrl() {
		return url;
	}

	public String getHash() {
		return hash;
	}

	public String getData() {
		return data;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}