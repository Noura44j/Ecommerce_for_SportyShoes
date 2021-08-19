<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Product List</div>
 
 
 
   <c:forEach items="${list}" var="prodInfo">
       <div class="product-preview-container">
           <ul>
               <li><img class="product-image"
                   src="${pageContext.request.contextPath}/img/img.png" /></li>
               <li>Code: ${prodInfo.code}</li>
               <li>Name: ${prodInfo.name}</li>
               <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
               <li>Category: ${prodInfo.category.name}</li>
               <li><a
                   href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
                       Buy Now</a></li>
                
           </ul>
       </div>
 
   </c:forEach>
   <br/>
  
  
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>