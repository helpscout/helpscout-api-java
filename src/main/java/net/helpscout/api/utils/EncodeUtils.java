package net.helpscout.api.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @Author: ivan
 * Date: 28.04.16
 * Time: 0:04
 */
public class EncodeUtils {

    public static String getEncoded(String val) {
        try {
            return Base64.encodeBase64String(val.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 should always be there!", e);
        }
    }

    public static byte[] getDecoded(String val) {
        return Base64.decodeBase64(val);
    }
}
