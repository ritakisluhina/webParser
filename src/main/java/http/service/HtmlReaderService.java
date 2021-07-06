package http.service;

import org.jsoup.select.Elements;

public interface HtmlReaderService {
    public Elements readHttp(String url);
}
