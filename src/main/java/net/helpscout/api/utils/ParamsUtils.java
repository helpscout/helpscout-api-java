package net.helpscout.api.utils;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ivan
 * Date: 28.04.16
 * Time: 0:29
 */
public class ParamsUtils {

    @SneakyThrows
    public static String setParams(String url, Map<String, String> params) {

        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            for (String key : params.keySet()) {
                appendParamSeparator(sb);
                String encodedParameter = URLEncoder.encode(params.get(key), "UTF-8");
                sb.append(key).append("=").append(encodedParameter);
            }
            return sb.toString();
        }
        return url;
    }

    public static String setFields(String url, List<String> fields) {

        if (fields != null && fields.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            appendParamSeparator(sb);
            String joinedParams = StringUtils.join(fields, ',');

            sb.append("fields=").append(joinedParams);
            url = sb.toString();
        }
        return url;
    }

    public static Map<String, String> getCustomerSearchParams(String email, String firstName, String lastName, Integer page) {

        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isNotBlank(email)) {
            params.put("email", email.trim().toLowerCase());
        }

        if (StringUtils.isNotBlank(firstName)) {
            params.put("firstName", firstName.trim());
        }

        if (StringUtils.isNotBlank(lastName)) {
            params.put("lastName", lastName.trim());
        }

        if (page != null && page > 0) {
            params.put("page", String.valueOf(page));
        }
        return params;
    }

    private static StringBuilder appendParamSeparator(StringBuilder url) {
        if (url.indexOf("?") > 0) {
            url.append("&");
        } else {
            url.append("?");
        }
        return url;
    }
}
