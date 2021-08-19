<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
   <jsp:include page="_header_admin.jsp" />
  
   <jsp:include page="_menu_admin.jsp" />
   
   <div class="page-title">Order Details</div>
         <c:forEach items="${orderDetails}" var="productInfo"
               varStatus="varStatus">
               <div class="product-preview-container">
                   <ul> 
                       <li>Code: ${productInfo.productCode}   
                       </li>
                       <li>Name: ${productInfo.productName }</li>
                       <li>Price: <span class="price"> ${productInfo.price}
                       </span></li>
                       
                       <li>Quantity: ${productInfo.quanity}</li>
                       <li> 
                       <li>Subtotal:
                         <span class="subtotal">${productInfo.amount} </span></span>
                       </li>
                   </ul>
               </div>
           </c:forEach>  
 
  
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>