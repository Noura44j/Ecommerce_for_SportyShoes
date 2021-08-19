package com.shop.beans;
   
 
public class ProductInfo {
    private String code;
    private String name;
    private String category;
    private double price;
 
    private boolean newProduct=false;
  
 
    public ProductInfo() {
    }
 
    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public ProductInfo(String code, String name, double price, String category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public ProductInfo(String code, String name, double price  ) {
        this.code = code;
        this.name = name;
        this.price = price; 
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
    
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
  
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
 
}