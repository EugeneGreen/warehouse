package ua.skillsup.practice.warehouse.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCountList {
    private List<ProductCount> products = new ArrayList<>();

    public ProductCountList() {

    }

    public ProductCountList(List<ProductCount> products) {
        this.products = products;
    }
    public List<ProductCount> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCount> products) {
        this.products = products;
    }
}
