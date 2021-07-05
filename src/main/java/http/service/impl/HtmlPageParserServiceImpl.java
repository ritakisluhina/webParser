package http.service.impl;

import http.model.Product;
import http.service.HtmlPageParserService;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlPageParserServiceImpl implements HtmlPageParserService {
    private List<Product> products;
    private HttpClient client = HttpClient.newHttpClient();

    @Override
    public List<Product> parse(HttpRequest request) {
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Document doc = Jsoup.parse(response.body().toString());
            Elements elements = doc.select("a[data-test-id=\"ProductTile\"]");
            for (Element element : elements) {
                String brandName = element.selectFirst("[data-test-id=\"BrandName\"]").text();
                String price = getPrice(element);

                Elements colorsElements = element.select("[data-test-id=\"ColorBubble\"]");
                List<String> colors = new ArrayList<>();
                for (Element color : colorsElements) {
                    colors.add(color.attr("color"));
                }
                String articleID = element.attr("id");
                products.add(new Product(brandName, colors, Float.parseFloat(price)));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get response" + e);
        }
        return products;
    }

    private String getPrice(Element element) {
        String price = "";
        if (element.selectFirst("[data-test-id=\"ProductPriceFormattedBasePrice\"]") == null) {
            if (element.selectFirst("[data-test-id=\"FormattedSalePrice\"]") == null) {
                price = element.selectFirst("[data-test-id=\"ProductPriceCampaignWithoutSale\"]")
                        .text();
            } else {
                price = element.selectFirst("[data-test-id=\"FormattedSalePrice\"]").text();
            }
        } else {
            price = element.selectFirst("[data-test-id=\"ProductPriceFormattedBasePrice\"]").text();
        }
        return price.replaceAll("[a-zA-Z\\s]", "")
                .replace(",", ".");
    }
}
