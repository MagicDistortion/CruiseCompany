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
<br><br><br><br>

          <c:if test="${shipsList==null}">
             <form action ="../admin/ships_for_add_cruise" method ="get" align="center">
             <input type="submit" class="btn btn-dark" value="${phrases['langAddCruise']}"/>
             </form>
          </c:if>

         <c:if  test="${shipsList!=null}" >
             <form action ="../admin/add_cruise" method ="post">
             <div align="center" >
           <table>
           <th ><h6 style="color:#fff" align="center"/> ${phrases['langPickAShip']} &nbsp</th>
           <th ><h6 style="color:#fff" align="center"/> ${phrases['langEnterCruiseName']} &nbsp</th>
           <th ><h6 style="color:#fff" align="center"/> ${phrases['langEnterNumberOfPorts']} &nbsp</th>
           <th ><h6 style="color:#fff" align="center"/> ${phrases['langEnterPrice']} &nbsp</th>
           <th ><h6 style="color:#fff" align="center"/> ${phrases['langPickStartTime']} &nbsp</th>
           <th ><h6 style="color:#fff" align="center"/> ${phrases['langPickEndTime']} &nbsp</th>
           <tr>
            <td> <select class="btn btn-primary dropdown-toggle" name="shipId" required>
                 <option disabled>${phrases['langPick']}</option>
                      <c:forEach items="${shipsList}" var="i">
                         <option value="${i.getId()}">${i.getName()}</option>
                      </c:forEach></select></td>
             <td><input name="cruiseName" class="btn btn-light" placeholder="${phrases['langEnterNameOfCruise']} " pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ\-]{2,32}" required/></td>
             <td><input name="numberOfPorts" class="btn btn-light" placeholder="${phrases['langEnterNumberOfPorts']} " pattern="[0-9]\d*"  size="35" required/></td>
             <td><input name="price" type="number" class="btn btn-light" style="width: 100px;" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}"  required/></td>
             <td><input class="form-control" type="datetime-local" name="startTime" style="width: 165px;" required/></td>
             <td><input class="form-control" type="datetime-local" name="endTime" style="width: 165px;" required/></td>
             <td><input type="submit" class="btn btn-warning"  value="${phrases['langAddCruise']}"/></td>
             </div>
             </form>
             </tr>
           </table>
         </c:if>
           </table>
           <br><br><br>
           <h4 style="color:#B22222" align="center">
              <c:forEach items="${errors}" var="i">
                 ${i}<br>
              </c:forEach>
           </h4>
    </body>
</html>
