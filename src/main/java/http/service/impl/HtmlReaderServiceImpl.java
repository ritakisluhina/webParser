package http.service.impl;

import http.service.HtmlReaderService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicInteger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlReaderServiceImpl implements HtmlReaderService {
    private AtomicInteger requestsCounter = new AtomicInteger();

    public HtmlReaderServiceImpl() {
    }

    public AtomicInteger getRequestsCounter() {
        return requestsCounter;
    }

    @Override
    public Elements readHttp(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            requestsCounter.incrementAndGet();
            Document document = Jsoup.parse(response.body().toString());
            return document.select("a[data-test-id=\"ProductTile\"]");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get response" + e);
        }
    }
}
