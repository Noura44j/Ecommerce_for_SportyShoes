<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
  
<body>
 
   <jsp:include page="_header_admin.jsp" />
   <jsp:include page="_menu_admin.jsp" />
 
   <div class="page-title">Product</div>
  
   <c:if test="${not empty message }">
     <div class="error-message">
         ${message}
     </div>
   </c:if>
 
   <form  action="addnewproduct" method="post">
       <table style="text-align:left;">
           <tr>
               <td>Code *</td>
               <td style="color:red;">
                   <input type="text" name="code" value="${productForm.code}"  required/> 
               </td> 
           </tr>
 
           <tr>
               <td>Category *</td>
               <td style="color:red;">
                   <select name="category">
                  		<c:forEach items="${category}" var="category">
                   			<option value="${category.id}">${category.name}</option>
   						</c:forEach>
                   </select>
               </td> 
           </tr>
 
           <tr>
               <td>Name *</td>
               <td><input type="text" name="name" value="${productForm.name}"   required/></td> 
           </tr>
 
           <tr>
               <td>Price *</td>
               <td><input type="number" name="price"  min="0"  value="${productForm.price}"  required/></td> 
           </tr>  
 
           <tr>
               <td>&nbsp;
               	  <c:if test="${productForm.code != null }">
				    <input type="hidden" name="update" value="${productForm.name}"  />
				  </c:if>
               </td>
               <td><input type="submit" value="Submit" /> <input type="reset"
                   value="Reset" /></td>
           </tr>
       </table>
   </form>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>