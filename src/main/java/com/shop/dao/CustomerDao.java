package com.shop.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.shop.beans.Customer;  
  
public class CustomerDao {  
	JdbcTemplate template;  
	Customer c = null;
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int save(Customer p){  
	    String sql="insert into customers(name,email,password) values('"+p.getName()+"','"+p.getEmail()+"','"+p.getPassword()+"')";  
	    return template.update(sql);  
	}  
	public int update(Customer p){  
	    String sql="update customers set name='"+p.getName()+"', email='"+p.getEmail()+"',password='"+p.getPassword()+"' where id="+p.getId()+"";  
	    return template.update(sql);  
	}  
	

	public int updateAdmin(Customer p){  
	    String sql="update admin set name='"+p.getName()+"', email='"+p.getEmail()+"',password='"+p.getPassword()+"' where id="+p.getId()+"";  
	    return template.update(sql);  
	}  
	
	
	public int delete(int id){  
	    String sql="delete from customers where id="+id+"";  
	    return template.update(sql);  
	}  
	public Customer getCustomerById(int id){  
	    String sql="select * from customers where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Customer>(Customer.class));  
	}  

	public Customer getAdminById(int id){  
	    String sql="select * from admin where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Customer>(Customer.class));  
	}  

	
	public boolean customerExist(String email, String password){  
	    String sql="select * from customers where email =? and password = ?";   
	    try {
	    	c = template.queryForObject(sql, new Object[]{email,password},new BeanPropertyRowMapper<Customer>(Customer.class));  
	    }catch(Exception e) {
	    	
	    }
	    return (c != null);
	}

	
	public boolean adminExist(String email, String password){  
	    String sql="select * from admin where email =? and password = ?";   
	    try {
	    	c = template.queryForObject(sql, new Object[]{email,password},new BeanPropertyRowMapper<Customer>(Customer.class));  
	    }catch(Exception e) {
	    	
	    }
	    return (c != null);
	}
	
	public Customer getCustomer() {
		return c;
	}
	
	public List<Customer> getCustomers(String name){  
		String sql = "";
		if(name.equals(""))
		{
			sql = "select * from customers";
		}else {
			sql = "select * from customers where name like '%"+name+"%'";
			
		}
	    return template.query(sql,new RowMapper<Customer>(){  
	        public Customer mapRow(ResultSet rs, int row) throws SQLException {  
	            Customer e=new Customer();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setEmail(rs.getString(3));  
	            return e;  
	        }  
	    });  
	}  
}  