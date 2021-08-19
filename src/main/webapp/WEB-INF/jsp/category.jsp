<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>

   <jsp:include page="_head.jsp" />
  
<body>
 
   <jsp:include page="_header_admin.jsp" />
   <jsp:include page="_menu_admin.jsp" />
 
   <div class="page-title">Add / Update Category</div>
  
   <c:if test="${not empty message }">
     <div class="error-message">
         ${message}
     </div>
   </c:if>
 
   <form  action="addnewcategory" method="post">
       <table style="text-align:left;"> 
 
           <tr>
               <td>Name *</td>
               <td><input type="text" name="name" value="${categ.name}"   required/></td> 
           </tr> 
 
           <tr>

               <td>&nbsp;
               	  <c:if test="${categ.id != null }">
				    <input type="hidden" name="update" value="${categ.id}"  />
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