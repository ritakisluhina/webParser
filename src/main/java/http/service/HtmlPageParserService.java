package http.service;

import http.model.Product;
import java.util.List;
import org.jsoup.select.Elements;

public interface HtmlPageParserService {
    public List<Product> parse(Elements elements);
}
