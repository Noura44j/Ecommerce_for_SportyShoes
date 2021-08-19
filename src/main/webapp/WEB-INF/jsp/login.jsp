<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>

   <jsp:include page="_head.jsp" />
<body>  
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" /> 
 
   <div class="page-title">Login</div>
 
   <div class="login-container">
 
      
 		<c:if test="${isAdmin == true}">
 			<h3>Enter Admin email and password</h3>
       		<br>  
      	 	<form method="POST"  action="login">
       </c:if>
 		<c:if test="${isAdmin != true }">
 			 <h3>Enter your email and password</h3>
      		 <br>  
      	 	<form method="POST"  action="custLogin">
       </c:if>
           <table>
               <tr>
                   <td>Email *</td>
                   <td><input type = "email" name="userName" required/></td>
               </tr>
 
               <tr>
                   <td>Password *</td>
                   <td><input type="password" name="password" required/></td>
               </tr>
 
               <tr>
                   <td>&nbsp;</td>
                   <td><input type="submit" value="Login" /> <input type="reset"
                       value="Reset" /></td>
               </tr>
               <c:if test="${isAdmin != true}">
		 			 <tr>
		                   <td>&nbsp;</td>
		                   <td><a href="register">Register now</a></td>
		               </tr>
		       </c:if>
               
           </table>
       </form>
 
       <span class="error-message">${error }</span>
 
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>