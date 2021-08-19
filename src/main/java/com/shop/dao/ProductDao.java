package com.shop.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.xdevapi.SessionFactory;
import com.shop.beans.Product;
import com.shop.beans.ProductInfo;  
  
public class ProductDao {  
	JdbcTemplate template;
	
	@Autowired
	CategoryDao categDao;
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int save(Product p){  
	    String sql="insert into products(Name,Price,Image, Create_Date) values('"+p.getName()+"',"+p.getPrice()+",'"+p.getImage()+"' , now())";  
	    return template.update(sql);  
	}  
	public int save(ProductInfo p){  
	    String sql="insert into products(Name, Price, Code, category_id , Create_Date) "
	    		+ "values('"+p.getName()+"',"+p.getPrice()+",'"+p.getCode()+"', '"+p.getCategory()+"' , now())";  
	    System.out.println(sql);
	    return template.update(sql);  
	}   
	public int update(ProductInfo p){  
	    String sql="update products set name='"+p.getName()+"', Price="+p.getPrice()+",category_id='"+p.getCategory()+"' where Code='"+p.getCode()+"'";  
	    return template.update(sql);  
	} 
	public int delete(int id){  
	    String sql="delete from products where id="+id+"";  
	    return template.update(sql);  
	}  
	public Product getProductById(int id){  
	    String sql="select * from products where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Product>(Product.class));  
	}  
	public List<Product> getProductloyees(){  
	    return template.query("select * from products",new RowMapper<Product>(){  
	        public Product mapRow(ResultSet rs, int row) throws SQLException {  
	            Product e=new Product();  
	            e.setCode(rs.getString(1));  
	            e.setCreateDate(rs.getDate(2));  
	            e.setName(rs.getString(4));  
	            e.setPrice(rs.getDouble(5));  
	            e.setImage(rs.getBytes(3)); 
	            e.setCategory(categDao.getCategoryById(rs.getInt(6)));
	            return e;  
	        }  
	    });  
	}
	public Product findProduct(String code) {
		// TODO Auto-generated method stub
	
	    String sql="select * from products where code=?";  
	    return template.queryForObject(sql, new Object[]{code},new BeanPropertyRowMapper<Product>(Product.class));  
	}  
	
	  
	
	public ProductInfo findProductInfo(String code) {
	    Product product = this.findProduct(code);
	    if (product == null) {
	        return null;
	    }
	    return new ProductInfo(product.getCode(), product.getName(), product.getPrice(), product.getCategoryId());
	}

  
}  