package http.model;

import java.util.List;

public class Product {
    private String brand;
    private List<String> color;
    private float price;

    public Product() {

    }

    public Product(String brand, List<String> color, float price) {
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{brand : '" + brand + '\''
                + ", color : " + color
                + ", price : " + price
                + '}';
    }
}
