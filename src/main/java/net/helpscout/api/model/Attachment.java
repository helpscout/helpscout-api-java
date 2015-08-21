package net.helpscout.api.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attachment {
    
	@Getter
	Long id;
	
	@Getter @Setter
	String mimeType;

	@Setter
	String fileName;
	
	@Getter
	int size;

	@Getter
	int width;
	
	@Getter
	int height;
	
	@Getter
	String url;
	
	@Getter @Setter
	String hash;
	
	@Getter @Setter
	String data;

	public boolean isImage() {
		return getMimeType() != null && getMimeType().startsWith("image");
	}

    // Don't use Lombok here in order to maintain backward-compatibility. This
    // method is named "getFilename", but should really be "getFileName". Lombok
    // unfortunately doesn't distinguish between upper and lower case when
    // generating methods, so we can't generate a new one and deprecate this
    // one.
    public String getFilename() {
		return fileName;
	}

	@Override
	public String toString() {
		String dataPreview = "";
		if (data != null && data.length() > 0) {
			if (data.length() < 10) {
				dataPreview = data;
			} else {
				dataPreview = data.substring(0, 10) + "...(" + data.length() + " chars in total)";
			}
		}
		return "Attachment [id=" + id + ", mimeType=" + mimeType + ", fileName=" + fileName + ", size=" + size
				+ ", width=" + width + ", height=" + height + ", url=" + url + ", hash=" + hash + ", data="
				+ dataPreview + "]";
	}

}