package com.product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Products {
    private String productId;
    private String title;
    private String brandId;
    private String brandName;
    private String categoryId;
    private String categoryName;
    public static List<Products> inputData = new ArrayList<>();

    public Products(){};
    public Products(String productId, String title, String brandId, String brandName, String categoryId, String categoryName){
        this.setProductId(productId);
        this.setTitle(title);
        this.setBrandId(brandId);
        this.setBrandName(brandName);
        this.setCategoryId(categoryId);
        this.setCategoryName(categoryName);
    }

    public void getData() throws IOException, FileNotFoundException {
        String fileName = "/data/sample_product_data.tsv";
        Path path = Paths.get(fileName);
        List<String>lines = Files.readAllLines(path);

        for(String inputLine: lines) {
            String[] line = inputLine.split("\t");
            Products p = new Products(line[0], line[1], line[2], line[3], line[4], line[5]);
            inputData.add(p);
        }
        System.out.println("Input Data Size: " + inputData.size());
        System.out.println("Data Loading is Done");
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
