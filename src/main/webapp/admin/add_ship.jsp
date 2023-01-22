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
            body {
            background:#808080;
            background-image: url(../images/add_ship.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
<br><br>
<h2 style="color:#000000" align="center">${phrases['langAddShip']}</h2>

             <form action ="../admin/add_ship" method ="post" enctype="multipart/form-data">
                <table align="center">
                        <th><h6 style="color:#000000" align="center"/> ${phrases['langEnterNameOfShip']} &nbsp</th>
                        <th><h6 style="color:#000000" align="center"/> ${phrases['langEnterCapacity']} &nbsp</th>
                        <th><h6 style="color:#000000" align="center"/> ${phrases['langAddImage']} &nbsp</th>
                    <tr>
                          <td><input name="name" class="btn btn-light" placeholder="${phrases['langEnterNameOfShip']} " pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ\- ]{2,32}" required/></td>
                          <td><input name="capacity" type="number" class="btn btn-light" placeholder="${phrases['langEnterCapacity']} " style="width: 170px;" required/></td>
                          <td><input type="file" name="file" required/></td>
                          <td><input type="submit" class="btn btn-info" value="${phrases['langAddShip']}"/></td>
                    </tr>
                </table>
                          <br>
                <h2 style="color:#B22222" align="center">
                    <c:if  test="${not empty error_message}" >${error_message}</c:if>
                </h2>
             </form>
                <footer style=" position: absolute; top: 90%; width: 100%; color:#fff">
                <hr> <p align="center" >© 2023 Oceania Cruises</p>
                </footer>
    </body>
</html>
