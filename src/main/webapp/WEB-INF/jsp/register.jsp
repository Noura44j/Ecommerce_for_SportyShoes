<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>

   <jsp:include page="_head.jsp" />
<body>  
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" /> 
 
   <div class="page-title">Login</div>
 
   <div class="login-container">
 
       <h3>Create new account</h3>
       <br> 
 
       <form method="POST"  action="register">
           <table>
               <tr>
                   <td>User Name *</td>
                   <td><input name="userName" required/></td>
               </tr>
 
               <tr>
                   <td>User Email *</td>
                   <td><input type="email" name="userEmail" required/></td>
               </tr>
 
               <tr>
                   <td>Password *</td>
                   <td><input type="password" name="password" required/></td>
               </tr>
 
               <tr>
                   <td>&nbsp;</td>
                   <td><input type="submit" value="Register" /> <input type="reset"
                       value="Reset" /></td>
               </tr> 
           </table>
       </form>
 
       <span class="error-message">${error }</span>
 
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>