package com.shop.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List; 
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.shop.beans.CartInfo;
import com.shop.beans.CartLineInfo;
import com.shop.beans.Customer;
import com.shop.beans.CustomerInfo;
import com.shop.beans.Order;
import com.shop.beans.OrderDetail;
import com.shop.beans.OrderDetailInfo;
import com.shop.beans.OrderInfo;
import com.shop.beans.Product; 


public class OrderDao  {


    @Autowired
    private ProductDao productDAO;
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  


	public int saveOrder(Order o){  
	    String sql="insert into orders(Amount"
	    		+ ",Customer_Address"
	    		+ ",Customer_Name"
	    		+ ",Customer_Email,"
	    		+ "Customer_Phone," 
	    		+ "Order_Num,"
	    		+ "cust_id) values('"+o.getAmount()+"',"+
				"'"+o.getCustomerAddress()+"',"+
				"'"+o.getCustomerName()+"',"+
				"'"+o.getCustomerEmail()+"',"+
				"'"+o.getCustomerPhone()+"',"+ 
				"'"+o.getOrderNum()+"',"+
				"'"+o.getCustomerId()+"')";  
	    return template.update(sql);  
	}  

	public int saveOrderDetails(OrderDetail od){  
	    String sql="insert into order_details(Amount,"
	    		+ "Price,"
	    		+ "Quanity,"
	    		+ "ORDER_ID,"
	    		+ "PRODUCT_ID) values('"+od.getAmount()+"',"+
				"'"+od.getPrice()+"',"+
				"'"+od.getQuanity()+"',"+
				"'"+od.getOrder().getOrderNum()+"',"+
				"'"+od.getProduct().getCode()+"')";  
	    return template.update(sql);  
	}  
    private int getMaxOrderNum() {
        String sql = "Select max(o.Order_Num) from orders o ";
        try {
        	return template.queryForObject(sql,  Integer.class);
        }catch(Exception e) {
        	return 0;
        }
    }
  
    public void saveOrder(CartInfo cartInfo) { 
        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();
  
        order.setOrderNum(orderNum); 
        order.setAmount(cartInfo.getAmountTotal());
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());
        order.setCustomerEmail(customerInfo.getEmail());
        order.setCustomerPhone(customerInfo.getPhone());
        order.setCustomerAddress(customerInfo.getAddress());
        order.setCustomerId(customerInfo.getId());

        saveOrder(order);
        
        List<CartLineInfo> lines = cartInfo.getCartLines();
 
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());
 
            String code = line.getProductInfo().getCode();
            Product product = this.productDAO.findProduct(code);
            detail.setProduct(product);
 
            saveOrderDetails(detail);
        }


        cartInfo.setOrderNum(orderNum);
    }
  
    public Order findOrder(String orderId) { 
        String sql="select * from orders where id=?";  
        return template.queryForObject(sql, new Object[]{orderId},new BeanPropertyRowMapper<Order>(Order.class));  
         
    }
  
 
  
    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(), //
                order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    } 
    /*

	public List<OrderDetailInfo> listOrderDetailInfos(String orderId){  
		String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";
	    return template.query(sql , new Object[]{orderId} ,new RowMapper<OrderDetailInfo>(){  
	        public OrderDetailInfo mapRow(ResultSet rs, int row) throws SQLException {  
	        	OrderDetailInfo e=new OrderDetailInfo();  
	        	e.setId(rs.getString(1)); 
	            e.setProductCode(rs.getString(2));  
	            e.setProductName(rs.getString(3));
	            e.setQuanity(rs.getInt(4));
	            e.setPrice(rs.getInt(5));   
	            e.setAmount(rs.getInt(6));  
	            
	            return e;  
	        }  
	    });  
	}  
*/

	public List<OrderDetailInfo> orderDetails(String orderId){  
		String sql = "SELECT o.id, d.PRODUCT_ID, p.Name , d.quanity, d.price, d.amount "
				+ "from orders o, order_details d, products p "
				+ "where o.id = d.ORDER_ID and p.Code = d.PRODUCT_ID and "
				+ "o.id =  ? ";
	    return template.query(sql , new Object[]{orderId} ,new RowMapper<OrderDetailInfo>(){  
	        public OrderDetailInfo mapRow(ResultSet rs, int row) throws SQLException {  
	        	OrderDetailInfo e=new OrderDetailInfo();  
	        	e.setId(rs.getString(1)); 
	            e.setProductCode(rs.getString(2));  
	            e.setProductName(rs.getString(3));
	            e.setQuanity(rs.getInt(4));
	            e.setPrice(rs.getInt(5));   
	            e.setAmount(rs.getInt(6));  
	            
	            return e;  
	        }  
	    });  
	}  

	public List<Order> findCustomerOrders(String custID){  

        String sql="select * from orders where cust_id =?";  
	    return template.query(sql , new Object[]{custID} ,new RowMapper<Order>(){  
	        public Order mapRow(ResultSet rs, int row) throws SQLException {  
	        	Order e=new Order();  
	        	e.setId(rs.getString(1)); 
	            e.setAmount(rs.getDouble(2));  
	            e.setCustomerAddress(rs.getString(3));
	            e.setCustomerEmail(rs.getString(4));
	            e.setCustomerName(rs.getString(5));
	            e.setCustomerPhone(rs.getString(6));   
	            e.setOrderDate(rs.getDate(7));     
	            e.setOrderNum(rs.getInt(8));    
	            
	            return e;  
	        }  
	    });  
	}  


	public List<Order> allCustomerOrders(String categ, String  date){  
		String sql="select * from orders "; 
		if(!categ.equals("")) {
			sql="select * from orders, order_details , products where orders.ID = Order_Num "
					+ "and order_details.PRODUCT_ID = products.Code "
					+ "and category_id = "+categ+" GROUP by Order_Num ";
		}

		if(!date.equals("")) {
			sql="select * from orders where DATE(Order_Date) = '"+date+"'";
		}

	    return template.query(sql , new Object[]{} ,new RowMapper<Order>(){  
	        public Order mapRow(ResultSet rs, int row) throws SQLException {  
	        	Order e=new Order();  
	        	e.setId(rs.getString(1)); 
	            e.setAmount(rs.getDouble(2));  
	            e.setCustomerAddress(rs.getString(3));
	            e.setCustomerEmail(rs.getString(4));
	            e.setCustomerName(rs.getString(5));
	            e.setCustomerPhone(rs.getString(6));   
	            e.setOrderDate(rs.getDate(7));     
	            e.setOrderNum(rs.getInt(8));    
	            
	            return e;  
	        }  
	    });  
	}  

 
}