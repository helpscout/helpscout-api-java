package net.helpscout.api.model;

import lombok.Getter;
import lombok.Setter;

public class Attachment {
    
    @Getter
    private Long id;
    
    @Getter @Setter
    private String mimeType;

    @Setter
    private String fileName;
    
    @Getter
    private int size;

    @Getter
    private int width;
    
    @Getter
    private int height;
    
    @Getter
    private String url;
    
    @Getter @Setter
    private String hash;
    
    @Getter @Setter
    private String data;

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