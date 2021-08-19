<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 

   <jsp:include page="_head.jsp" />
 
 
   <jsp:include page="_header.jsp" />
   <c:if test="${isAdmin == null }">
   	<jsp:include page="_menu.jsp" />
   
   </c:if>
 
   <div class="page-title">Account Info</div>
 
   <div class="account-container">
 
 
       <h3>Update your account</h3>
       <br> 
 
       <form method="POST"  action="updateAccount">
           <table>
               <tr>
                   <td>User Name *</td>
                   <td><input name="userName" value="<c:out value="${userDetails.name}" />" required/></td>
               </tr>
 
               <tr>
                   <td>User Email *</td>
                   <td><input type="email" name="userEmail" value="<c:out value="${userDetails.email}" />" required/></td>
               </tr>
 
               <tr>
                   <td>Password *</td>
                   <td><input type="password" name="password" required/></td>
               </tr>
 
               <tr>
                   <td>&nbsp;</td>
                   <td><input type="submit" value="Update" /> <input type="reset"
                       value="Reset" /></td>
               </tr> 
           </table>
       </form>
       <span class="error-message">${msg }</span>
 
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>