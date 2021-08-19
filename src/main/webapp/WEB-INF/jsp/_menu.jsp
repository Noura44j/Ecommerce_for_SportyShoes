<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/">Home</a>
   |
    
        <c:if test="${sessionScope.custLogin != null || sessionScope.custLogin == true}"> 
			<a href="${pageContext.request.contextPath}/productList">
		      Product List
		   </a>
	        &nbsp;|&nbsp;
	         <a href="${pageContext.request.contextPath}/shoppingCart">
			      My Cart
			   </a>
	        &nbsp;|&nbsp;
	         <a href="${pageContext.request.contextPath}/myorders">
			      My Orders
			   </a>
        </c:if>
  
</div>