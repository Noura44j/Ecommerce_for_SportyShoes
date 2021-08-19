package com.shop.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.shop.beans.Category;  
  
public class CategoryDao {  
	JdbcTemplate template;  
	Category c = null;
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int save(Category p){  
	    String sql="insert into category(name) values('"+p.getName()+"')";  
	    return template.update(sql);  
	}  
	public int update(Category p){  
	    String sql="update category set name='"+p.getName()+"' where id="+p.getId()+"";  
	    return template.update(sql);  
	}  
	public int delete(int id){  
	    String sql="delete from category where id="+id+"";  
	    return template.update(sql);  
	}  
	public Category getCategoryById(int id){  
	    String sql="select * from category where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Category>(Category.class));  
	}  
	
	 
	
	public List<Category> getCategorys(){  
	    return template.query("select * from category",new RowMapper<Category>(){  
	        public Category mapRow(ResultSet rs, int row) throws SQLException {  
	            Category e=new Category();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));     
	            return e;  
	        }  
	    });  
	}  
}  