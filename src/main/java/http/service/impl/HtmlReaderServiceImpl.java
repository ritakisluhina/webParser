package http.service.impl;

import http.service.HtmlReaderService;
import java.net.URI;
import java.net.http.HttpRequest;

public class HtmlReaderServiceImpl implements HtmlReaderService {
    @Override
    public HttpRequest readHttp(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
    }
}
