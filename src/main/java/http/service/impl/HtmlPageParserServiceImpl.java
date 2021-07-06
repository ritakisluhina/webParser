package http.service.impl;

import http.model.Product;
import http.service.HtmlPageParserService;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlPageParserServiceImpl implements HtmlPageParserService {

    @Override
    public List<Product> parse(Elements elements) {
        List<Product> products = new ArrayList<>();
        for (Element element : elements) {
            String brandName = element.selectFirst("[data-test-id=\"BrandName\"]").text();
            String price = getPrice(element);
            Elements colorsElements = element.select("[data-test-id=\"ColorBubble\"]");
            List<String> colors = new ArrayList<>();
            for (Element color : colorsElements) {
                colors.add(color.attr("color"));
            }
            products.add(new Product(brandName, colors, Float.parseFloat(price)));
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
