<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
 
   <jsp:include page="_header_admin.jsp" />
   <jsp:include page="_menu_admin.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">All submitted orders</div>
 
 
 	<div>
 		<form action method="post">
 			<input type="text" name="date" > <input type="submit" value="Filter by Date"/>
 			<br/>date format[yyyy-mm-dd]
 		</form>
 		<br/>
 		<form action method="post"> 
              <select name="category">
             		<c:forEach items="${category}" var="category">
              			<option value="${category.id}">${category.name}</option>
				</c:forEach>
              </select> 
             <input type="submit" value="Filter by category"/> 
 		</form>
 	</div>
 <table border='1' width='100%'>
 	<tr>
	 	<th>Order ID</th>
	 	<th>Date</th>
	 	<th>Amount</th>
	 	<th>Customer Name</th>
	 	<th>Order Details</th>
 	</tr>
 	<c:forEach items="${orders}" var="orderInfo">
       <tr >
           <td> ${orderInfo.id}</td>
           <td> ${orderInfo.orderDate}</td>
           <td> ${orderInfo.amount}</td>
           <td> ${orderInfo.customerName}</td>
           <td> <a
                   href="${pageContext.request.contextPath}/admin/vieworderdetail?id=${orderInfo.id}">
                       View details</a></td>  
       </tr>
 
   </c:forEach>
 </table>
   
   <br/>
  
  
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>