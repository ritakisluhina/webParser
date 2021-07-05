package http.service;

import java.net.http.HttpRequest;

public interface HtmlReaderService {
    public HttpRequest readHttp(String webPage);
}
