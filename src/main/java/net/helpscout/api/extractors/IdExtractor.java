package net.helpscout.api.extractors;

import net.helpscout.api.ResultExtractor;
import net.helpscout.api.HTTPConnectionWrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ivan
 * Date: 20.05.16
 * Time: 1:15
 */
public class IdExtractor implements ResultExtractor<Long> {

    public Long extract(HTTPConnectionWrapper conn) {
        String location = conn.getConnection().getHeaderField("LOCATION");
        if (StringUtils.isNotBlank(location)) {
            return new Long(location.substring(
                    location.lastIndexOf("/") + 1,
                    location.lastIndexOf(".")));
        } else {
            return null;
        }
    }
}
