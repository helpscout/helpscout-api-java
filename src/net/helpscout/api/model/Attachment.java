package net.helpscout.api.model;

public class Attachment {
	private int id;
	private String mimeType;
	private String filename;
	private int size;
	private int width;
	private int height;
	private String url;
	
	public int getId() {
		return id;
	}
	public String getMimeType() {
		return mimeType;
	}
	public String getFilename() {
		return filename;
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