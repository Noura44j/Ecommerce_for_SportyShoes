<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
 
<body>
 
   <jsp:include page="_header_admin.jsp" />
   <jsp:include page="_menu_admin.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Category List</div>
 
 
 
   <c:forEach items="${list}" var="categ">
       <div class="product-preview-container">
           <ul> 
               <li>Name: ${categ.name}</li>  
               <li><a style="color:red;"
                   href="${pageContext.request.contextPath}/admin/addcategory?id=${categ.id}">
                     Edit Category</a></li>
           </ul>
       </div>
 
   </c:forEach>
   <br/>
  
  
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>