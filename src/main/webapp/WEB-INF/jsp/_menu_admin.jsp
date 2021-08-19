<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/admin/home">Home</a>
   |
    
        <c:if test="${sessionScope.adminLogin != null || sessionScope.adminLogin == true}"> 
			<a href="${pageContext.request.contextPath}/admin/productList">
		      Product List
		   </a>
	         
        </c:if>
  
</div>