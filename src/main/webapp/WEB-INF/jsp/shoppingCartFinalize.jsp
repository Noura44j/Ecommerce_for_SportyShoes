<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
 
   <jsp:include page="_header.jsp" />
 
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Finalize</div>
  
   <div class="container">
       <h3>Thank you for Order</h3>
       Your order number is: ${lastOrderedCart.orderNum}
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>