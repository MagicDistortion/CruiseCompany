<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>CruiseCompany
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
    <body>
        <c:if test="${empty user}">
           <br><br><br><br><br><br><br>
               <form action ="login" method ="post">
                   <div align="center" >
                        <table >
                            <tr>
	                          <td style="color:#0000ff"><h3/>${phrases['langLogin']}:</td>
	                          <td><input  name="login" class="form-control" placeholder="${phrases['langEnterLogin']}"  required/></td>
                            </tr>
                            <tr>
    	                      <td style="color:#ffff00" /><h3/>${phrases['langPassword']}:</td>
	                          <td><input type="password" class="form-control" name="password" placeholder="${phrases['langEnterPassword']}" required/></td>
	                        </tr>

                        </table>
    	                <input type="submit" class="btn btn-primary" value=${phrases['langGo']} /><br>
                           <h2 style="color:#B22222">
                             <c:if  test="${not empty mes}" >${mes}</c:if>
                           </h2>
                   </div>
               </form>
        </c:if>
  </body>
</html>