package com.shop.beans;
  
import java.util.Date;
  
  
public class Order  {
  
 
    private String id;
    protected Date orderDate;
    private int orderNum;
    private double amount;
 
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;
    private String customerId;
  
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
   
    public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
  
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
  
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }
 
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
  
    public String getCustomerAddress() {
        return customerAddress;
    }
 
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    } 
    
    public String getCustomerEmail() {
        return customerEmail;
    }
 
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
  
    public String getCustomerPhone() {
        return customerPhone;
    }
 
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
}