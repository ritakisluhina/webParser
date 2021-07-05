package http;

import http.model.Product;
import http.service.HtmlPageParserService;
import http.service.HtmlReaderService;
import http.service.ReportCreatorService;
import http.service.impl.HtmlPageParserServiceImpl;
import http.service.impl.HtmlReaderServiceImpl;
import http.service.impl.ReportCreatorServiceImpl;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What web page do you want to parse?");
        String url = scanner.next();
        System.out.println("Where do you want to save json file?");
        String filePath = scanner.next();

        HtmlReaderService htmlReaderService = new HtmlReaderServiceImpl();
        HttpRequest request = htmlReaderService.readHttp(url);

        HtmlPageParserService htmlPageParserService = new HtmlPageParserServiceImpl();
        List<Product> productList = htmlPageParserService.parse(request);

        ReportCreatorService reportCreatorService = new ReportCreatorServiceImpl();
        reportCreatorService.getProductReport(productList, filePath);
        System.out.println("Data was written to file");
    }
}
