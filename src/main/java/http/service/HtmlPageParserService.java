package http.service;

import http.model.Product;
import java.net.http.HttpRequest;
import java.util.List;

public interface HtmlPageParserService {
    public List<Product> parse(HttpRequest request);
}
