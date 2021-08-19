<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
 
   <jsp:include page="_header_admin.jsp" />
   <jsp:include page="_menu_admin.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">All Registered Customers</div>
 
 	<div>
 		<form action method="post">
 			<input type="text" name="name"> <input type="submit" value="Search by customer name"/>
 		</form>
 	</div>
 
 <table border='1' width='100%'>
 	<tr>
	 	<th>ID</th>
	 	<th>Name</th>
	 	<th>Email</th> 
 	</tr>
 	<c:forEach items="${customers}" var="customer">
       <tr >
           <td> ${customer.id}</td>
           <td> ${customer.name}</td>
           <td> ${customer.email}</td> 
       </tr>
 
   </c:forEach>
 </table>
   
   <br/>
  
  
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>