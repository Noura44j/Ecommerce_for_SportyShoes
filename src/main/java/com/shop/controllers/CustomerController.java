package com.shop.controllers;   
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.beans.CartInfo;
import com.shop.beans.Customer;
import com.shop.beans.CustomerInfo; 
import com.shop.beans.Order;
import com.shop.beans.OrderDetailInfo;
import com.shop.beans.Product;
import com.shop.beans.ProductInfo;
import com.shop.dao.CustomerDao; 
import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shopping.utils.Utils;  
@Controller  
public class CustomerController {   

    @Autowired  
    ProductDao prodDao;
    
    @Autowired  
    CustomerDao custDao;
    
    @Autowired
    private OrderDao orderDAO;
       

    @RequestMapping({ "/" })
    public String indexHandler(Model m,HttpSession httpSession){ 
 
        return "index";  
    }   
 
    


    @RequestMapping({ "/productList" })
    public String listProductHandler(Model m){  
        List<Product> list=prodDao.getProductloyees();  
        m.addAttribute("list",list);
        return "productList";  
    }   
 
    @RequestMapping(value="/custLogin",method = RequestMethod.POST)  
    public String CustLoginHandler(Model m, 
    		@RequestParam("userName") String userName,
    		@RequestParam("password") String password,
    		HttpSession httpSession){ 
    	if(custDao.customerExist(userName, password)) {   
    		httpSession.setAttribute("custLogin", true); 
    		httpSession.setAttribute("custID",custDao.getCustomer().getId());
    		httpSession.setAttribute("custName",custDao. getCustomer().getName()); 
            return "index";   
    	}else {
    		m.addAttribute("error","Invalid User");
    	}
        return "login";  
    }   
    



    @RequestMapping({ "/logout" })
    public String listProductHandler(Model m,
    		HttpSession httpSession){   
        httpSession.invalidate();   
        return "redirect:login";  
    }   
 
    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
 
        Product product = null;
        if (code != null && code.length() > 0) {
            product = prodDao.findProduct(code);
        }
        if (product != null) {
 
            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.addProduct(productInfo, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    
    @RequestMapping({ "/login" })
    public String adminHandler(Model m){ 
        return "login";  
    }   
    
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") CartInfo cartForm) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
 
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
    

    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model,
    		HttpSession httpSession){ 
    	
    	if(httpSession.getAttribute("custLogin")== null || !(boolean)httpSession.getAttribute("custLogin")) {   
    		model.addAttribute("error","You have to log in first"); 
            return "login";    
    	} 
        CartInfo myCart = Utils.getCartInSession(request);
 
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }
 

    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
      
        // Cart is empty.
        if (cartInfo.isEmpty()) {
             
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }
 
        model.addAttribute("customerForm", customerInfo);
 
        return "shoppingCartCustomer";
    }
    

    // POST: Save customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("customerForm") @Validated CustomerInfo customerForm ,
    		HttpSession httpSession) {
  
        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request); 
        String custId = Integer.toString((int)httpSession.getAttribute("custID"));
        customerForm.setId(custId);
        cartInfo.setCustomerInfo(customerForm );
  
        return "redirect:/shoppingCartConfirmation";
    }
    

    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
  
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
 
        return "shoppingCartConfirmation";
    }
    

    // POST: Send Cart (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST) 
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) { 
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) { 
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) { 
        	System.out.println(e);
            return "shoppingCartConfirmation";
        } 
        Utils.removeCartInSession(request);
          
        Utils.storeLastOrderedCartInSession(request, cartInfo);
  
        return "redirect:/shoppingCartFinalize";
    }
     

    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
 
        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
 
        return "shoppingCartFinalize";
    }
 

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = prodDao.findProduct(code);
        }
        if (product != null) {
 
            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.removeProduct(productInfo);
 
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
    
    

    
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerHandler(HttpServletRequest request, //
            Model model, // 
    		@RequestParam("userName") String userName,
    		@RequestParam("userEmail") String userEmail,
    		@RequestParam("password") String password) {
  
    	Customer c = new Customer();
    	c.setEmail(userEmail);
    	c.setName(userName);
    	c.setPassword(password);
    	if(custDao.save(c) > 0) {
    		model.addAttribute("error", "You have successfully register, you can login now!");
    		return "login";
    	}else {
    		model.addAttribute("error", "Some thing goes worng");
            return "register";
    	}
    }
    
 
    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String registerHandler(HttpServletRequest request, Model model,
    		HttpSession httpSession){  
    	if(httpSession.getAttribute("custLogin")!= null ) {    
            return "index";    
    	} 

        return "register";
    }


    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model, HttpSession httpSession) {
    	if(httpSession.getAttribute("custLogin")== null ) {    
            return "redirect:/login";    
    	} 
    	int id = (int)httpSession.getAttribute("custID");
        Customer userDetails = custDao.getCustomerById(id);
 
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
 

    
    @RequestMapping(value = { "/updateAccount" }, method = RequestMethod.POST)
    public String updateHandler(HttpServletRequest request, //
            Model model, // 
    		@RequestParam("userName") String userName,
    		@RequestParam("userEmail") String userEmail,
    		@RequestParam("password") String password, HttpSession httpSession) {
  
    	if(httpSession.getAttribute("custLogin")== null ) {    
            return "redirect:/login";    
    	} 
    	int id = (int)httpSession.getAttribute("custID");
    	Customer c = new Customer();
    	c.setId(id);
    	c.setEmail(userEmail);
    	c.setName(userName);
    	c.setPassword(password);
    	if(custDao.update(c) > 0) {
    		model.addAttribute("msg", "You have successfully update your account!"); 
            Customer userDetails = custDao.getCustomerById(id);
            
            model.addAttribute("userDetails", userDetails);
    	}else {
    		model.addAttribute("msg", "Some thing goes worng");
    	}
        return "accountInfo";
    }


    @RequestMapping({ "/myorders" })
    public String myOrdersHandler(Model m,HttpSession httpSession){ 

    	if(httpSession.getAttribute("custLogin")== null ) {    
            return "redirect:/login";    
    	} 
    	int id = (int)httpSession.getAttribute("custID");
    	
        List<Order> list= orderDAO.findCustomerOrders(Integer.toString(id));  
        m.addAttribute("orders",list);
        return "orderList";  
    }   
 
    

    @RequestMapping({ "/vieworderdetail" })
    public String listOrderDetailsHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "id", defaultValue = "") String orderId) {
 
    	List<OrderDetailInfo> list=  null;
        if (orderId != null && orderId.length() > 0) {
            list = orderDAO.orderDetails(orderId);
        } 
        System.out.println(list);
        model.addAttribute("orderDetails", list);
        return "orderDetails"; 
    }
}  