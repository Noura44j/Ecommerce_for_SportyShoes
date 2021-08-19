package com.shop.controllers;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource; 
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.beans.Category;
import com.shop.beans.Customer;
import com.shop.beans.Order;
import com.shop.beans.OrderDetailInfo;
import com.shop.beans.OrderInfo;
import com.shop.beans.Product;
import com.shop.beans.ProductInfo;
import com.shop.dao.CategoryDao;
import com.shop.dao.CustomerDao;
import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;

@Controller  
public class AdminController {
 
    @Autowired
    private OrderDao orderDAO;
 
    @Autowired
    private ProductDao productDAO;

    @Autowired  
    CustomerDao custDao;

    @Autowired  
    CategoryDao categDao;

    // GET: Show Login Page
    @RequestMapping(value = { "/admin/home" }, method = RequestMethod.GET)
    public String home(Model model, HttpSession httpSession) {

    	if(httpSession.getAttribute("adminLogin")== null || !(boolean)httpSession.getAttribute("adminLogin")) {   
    		model.addAttribute("error","You have to log in first"); 
            return "redirect:/admin/login";    
    	}  
        return "admin_index";
    }

    // GET: Show Login Page
    @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
    public String login(Model model, @RequestParam(value = "error", defaultValue = "") String error) {
    	model.addAttribute("isAdmin", true);
    	if(error != null || !error.equals(""))
        	model.addAttribute("error", error);
        return "login";
    }

    
    // POST: Login Process
    @RequestMapping(value = { "/admin/login" }, method = RequestMethod.POST)
    public String loginHandler(Model model,
    		@RequestParam("userName") String userName,
    		@RequestParam("password") String password,
    		HttpSession httpSession){ 
    	if(custDao.adminExist(userName, password)) {   
    		httpSession.setAttribute("adminLogin", true); 
    		httpSession.setAttribute("adminID",custDao.getCustomer().getId());
    		httpSession.setAttribute("adminName",custDao. getCustomer().getName());
            return "redirect:/admin/home"; 
    	}else {
    		model.addAttribute("error","Invalid Admin");
    	}
        return "redirect:/admin/login";  
    }   
     
 
    @RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
  
 
        //model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }
 
    // GET: Show product.
    @RequestMapping(value = { "/admin/addproduct" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;
 
        if (code != null && code.length() > 0) {
            productInfo = productDAO.findProductInfo(code);
        }
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setNewProduct(true);
        }
        List<Category> category=categDao.getCategorys();  
        model.addAttribute("category",category);
        model.addAttribute("productForm", productInfo);
        return "product";
    }
 
    // POST: Save product
    @RequestMapping(value = { "/admin/addnewproduct" }, method = RequestMethod.POST) 
    public String productSave(Model model, // 
    		@RequestParam("code") String code,
    		@RequestParam("price") Double price,
    		@RequestParam("name") String name, 
    		@RequestParam("category") String category, 
    		@RequestParam(value = "update", defaultValue = "") String update, 
    		HttpSession httpSession) { 
    	 ProductInfo pInfo = new ProductInfo(code, name, price , category);
        try {
        	if(update == null)
        		productDAO.save(pInfo);
        	else
        		productDAO.update(pInfo);
        } catch (Exception e) { 
            String message = e.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "product";
 
        }
        return "redirect:/admin/productList";
    }



    @RequestMapping({ "/admin/productList" })
    public String listProductHandler(Model m){  
        List<Product> list=productDAO.getProductloyees();  
        m.addAttribute("list",list);
        return "adminproductList";  
    }   

    
    
    
    
    
    

    // GET: Show category.
    @RequestMapping(value = { "/admin/addcategory" }, method = RequestMethod.GET)
    public String category(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
        Category categ = null;
 
        if (id != null && id.length() > 0) {
        	categ = categDao.getCategoryById(Integer.parseInt(id));
        }
        if (categ == null) {
        	categ = new Category(); 
        } 
        List<Category> categories=categDao.getCategorys();  
        model.addAttribute("list",categories);  
        model.addAttribute("categ",categ); 
        return "category";
    }
 
    // POST: Save category
    @RequestMapping(value = { "/admin/addnewcategory" }, method = RequestMethod.POST) 
    public String productSave(Model model, // 
    		@RequestParam("name") String name, 
    		@RequestParam(value = "update", defaultValue = "") String update,  
    		HttpSession httpSession) { 
    	Category categ = new Category();
    	categ.setName(name);
        try {
        	if(update == null || update.equals("")) {
        		categDao.save(categ);  
        	}else {
            	categ.setId(Integer.parseInt(update));
        		categDao.update(categ); 
        	}
        } catch (Exception e) { 
            String message = e.getMessage();
            model.addAttribute("message", message); 
            return "category";
 
        }
        return "redirect:/admin/categoryList";
    }


    @RequestMapping({ "/admin/categoryList" })
    public String categoryListHandler(Model m){  
        List<Category> list=categDao.getCategorys();  
        m.addAttribute("list",list);
        return "categoryList";  
    }   

    
    
    @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/admin/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.orderDetails(orderId);
        orderInfo.setDetails(details);
 
        model.addAttribute("orderInfo", orderInfo);
 
        return "order";
    }


    @RequestMapping(value = { "/admin/accountInfo" }, method = RequestMethod.GET)
    public String adminAccountInfo(Model model, HttpSession httpSession,
    		 @RequestParam(value = "msg", defaultValue = "") String msg) {
    	if(httpSession.getAttribute("adminLogin")== null ) {    
            return "redirect:/login";    
    	} 
    	int id = (int)httpSession.getAttribute("adminID");
        Customer userDetails = custDao.getAdminById(id);

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("msg", msg);
        model.addAttribute("isAdmin", true);
        return "accountInfo";
    }
 

    
    @RequestMapping(value = { "/admin/updateAccount" }, method = RequestMethod.POST)
    public String updateAccHandler(HttpServletRequest request, //
            Model model, // 
    		@RequestParam("userName") String userName,
    		@RequestParam("userEmail") String userEmail,
    		@RequestParam("password") String password, HttpSession httpSession) {
  
    	if(httpSession.getAttribute("adminLogin")== null ) {    
            return "redirect:/login";    
    	} 
    	int id = (int)httpSession.getAttribute("adminID");
    	Customer c = new Customer();
    	c.setId(id);
    	c.setEmail(userEmail);
    	c.setName(userName);
    	c.setPassword(password);
    	if(custDao.updateAdmin(c) > 0) {
    		model.addAttribute("msg", "You have successfully update your account!"); 
            Customer userDetails = custDao.getAdminById(id); 
            model.addAttribute("userDetails", userDetails);
    	}else {
    		model.addAttribute("msg", "Some thing goes worng");
    	}
        return "redirect:/admin/accountInfo";
    }


    @RequestMapping({ "/admin/listorderss" })
    public String myOrdersHandler(Model m,HttpSession httpSession,
    		 @RequestParam(value = "category", defaultValue = "") String categ,
    		 @RequestParam(value = "date", defaultValue = "") String date){ 

    	if(httpSession.getAttribute("adminLogin")== null ) {    
            return "redirect:/login";    
    	}  
        List<Order> list= orderDAO.allCustomerOrders(categ, date);  
        List<Category> category=categDao.getCategorys();  
        m.addAttribute("category",category);
        m.addAttribute("orders",list);
        return "adminOrderList";  
    }   
 
    

    @RequestMapping({ "/admin/vieworderdetail" })
    public String adminListOrderDetailsHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "id", defaultValue = "") String orderId) {
 
    	List<OrderDetailInfo> list=  null;
        if (orderId != null && orderId.length() > 0) {
            list = orderDAO.orderDetails(orderId);
        } 
        System.out.println(list);
        model.addAttribute("orderDetails", list);
        return "adminOrderDetails"; 
    }
    
    


    @RequestMapping({ "/admin/listCustomers" })
    public String lisrCustomersHandler(Model m,HttpSession httpSession,
    		@RequestParam(value = "name", defaultValue = "") String name){ 

    	if(httpSession.getAttribute("adminLogin")== null ) {    
            return "redirect:/login";    
    	}  
        List<Customer> list= custDao.getCustomers(name);  
        m.addAttribute("customers",list);
        return "customerList";  
    }   
}