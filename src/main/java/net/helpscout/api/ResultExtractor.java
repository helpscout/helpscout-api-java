package net.helpscout.api;

import java.net.HttpURLConnection;

public interface ResultExtractor<T> {

    T extract(HttpURLConnection conn);

}
