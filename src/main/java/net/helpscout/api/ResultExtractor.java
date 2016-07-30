package net.helpscout.api;

public interface ResultExtractor<T> {

    T extract(HTTPConnectionWrapper conn);

}
