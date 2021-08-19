package com.shop.beans;
  
import java.util.Date;
  
  
public class Product {
  
 
    private String code;
    private String name;
    private Category category;
    private String category_id;
    private double price;
    private byte[] image;
 
    // For sort.
    private Date createDate;
 
    public Product() {
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
  
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
   
    public Date getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategoryId() {
    	return category_id;
    }


    public String setCategoryId(String category_id) {
    	return category_id;
    }

    public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public void setCategory(Category categ) {
    	this.category = categ;
    }
    
    public Category getCategory() {
    	return category;
    }
 
}