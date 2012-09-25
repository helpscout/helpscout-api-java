package net.helpscout.api.model;

public class Attachment {
	private Long id;
	private String mimeType;
	private String fileName;
	private int size;
	private int width;
	private int height;
	private String url;

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

}