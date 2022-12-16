<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Add ship
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000}
                </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
<br><br><br><br><br><br><br>

             <form action ="../admin/add_ship" method ="post">
             <div align="center" >
                    <input name="name" class="btn btn-light" placeholder="${phrases['langEnterNameOfShip']} " pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ\-]{2,32}" required/>
                    <input name="capacity" type="number" class="btn btn-light" placeholder="${phrases['langEnterCapacity']} " style="width: 170px;" required/>
                    <input name="current_point" class="btn btn-light" placeholder="${phrases['langEnterCurrentPoint']} " pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}" required/>

                    <input type="submit" class="btn btn-warning"  value="${phrases['langAddShip']}"/><br>
                    <h2 style="color:#B22222">
                        <c:if  test="${not empty error_message}" >${error_message}</c:if>
                    </h2>
             </div>
             </form>
    </body>
</html>
