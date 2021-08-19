<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="header-container">
 
    <div class="site-name">Sporty Shoes</div>
 
    <div class="header-bar">
     
        <c:if test="${sessionScope.adminLogin == null || sessionScope.adminLogin != true}"> 
            <a href="${pageContext.request.contextPath}/admin/login">Login</a>
        </c:if>
        <c:if test="${sessionScope.adminLogin != null || sessionScope.adminLogin == true}"> 
			Hello
          	<a href="${pageContext.request.contextPath}/admin/accountInfo">accountInfo</a>
	        &nbsp;|&nbsp;
	        <a href="${pageContext.request.contextPath}/logout">Logout</a>
	 
        </c:if>
    </div>
</div>