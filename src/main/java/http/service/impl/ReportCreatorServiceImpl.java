package http.service.impl;

import http.model.Product;
import http.service.ReportCreatorService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class ReportCreatorServiceImpl implements ReportCreatorService {
    @Override
    public void getProductReport(List<Product> products, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filePath), products);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file products!" + e);
        }
    }
}
