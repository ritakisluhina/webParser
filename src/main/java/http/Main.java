package http;

import http.model.Product;
import http.service.HtmlPageParserService;
import http.service.HtmlReaderService;
import http.service.ReportCreatorService;
import http.service.impl.HtmlPageParserServiceImpl;
import http.service.impl.HtmlReaderServiceImpl;
import http.service.impl.ReportCreatorServiceImpl;
import java.util.List;
import java.util.Scanner;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What web page do you want to parse?");
        String url = scanner.next();
        System.out.println("Where do you want to save json file?");
        String filePath = scanner.next();

        HtmlReaderService htmlReaderService = new HtmlReaderServiceImpl();
        Elements elements = htmlReaderService.readHttp(url);

        HtmlPageParserService htmlPageParserService = new HtmlPageParserServiceImpl();
        List<Product> productList = htmlPageParserService.parse(elements);

        ReportCreatorService reportCreatorService = new ReportCreatorServiceImpl();
        reportCreatorService.getProductReport(productList, filePath);

        System.out.println("Amount of triggered HTTP requests: "
                + ((HtmlReaderServiceImpl) htmlReaderService).getRequestsCounter().toString());
        System.out.println("Amount of extracted products: " + productList.size());
        System.out.println("Data was written to file");
    }
}
