<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">My Orders List</div>
 
 
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
                   href="${pageContext.request.contextPath}/vieworderdetail?id=${orderInfo.id}">
                       View details</a></td>  
       </tr>
 
   </c:forEach>
 </table>
   
   <br/>
  
  
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>