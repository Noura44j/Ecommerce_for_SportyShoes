<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

   <jsp:include page="_head.jsp"/>
<body>
  
   <jsp:include page="_header_admin.jsp"/> 
 
   <jsp:include page="_menu_admin.jsp"/> 
 
   <div class="page-title">E-commerce for Sporty Shoes</div>
  
   <div class="demo-container">
   <h3>Welcome to administrator</h3>
  	You can add, edit or delete products
   <ul>
      <li><a href="addcategory">Add new category</a></li>
      <li><a href="categoryList">List of categories</a></li>
      <li><a href="addproduct">Add new product</a></li>
      <li><a href="productList">List of products</a></li>
      <li><a href="listorderss">List of Orders</a></li> 
      <li><a href="listCustomers">List of Customers</a></li> 
   </ul>
   </div>
  
  
   <jsp:include page="_footer.jsp" />
 
</body>
</html>