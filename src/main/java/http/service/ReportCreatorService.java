package http.service;

import http.model.Product;
import java.util.List;

public interface ReportCreatorService {
    public void getProductReport(List<Product> products, String filePath);
}
