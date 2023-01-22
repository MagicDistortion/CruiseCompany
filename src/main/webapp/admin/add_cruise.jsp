<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Add cruise
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(../images/add_cruise.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
    <h2 style="color:#fff" align="center">${phrases['langAddCruise']}</h2>
        <form action ="../admin/ships_for_add_cruise" method ="get">
        <table align="center">
           <th style="color:#00FFFF"><h4/> ${phrases['langPickStartTime']} &nbsp</th>
           <th style="color:#00FFFF"><h4/> ${phrases['langPickEndTime']} &nbsp</th>
            <tr>
                 <td><input class="form-control" type="datetime-local" name="startTime"
                 <c:choose>
                   <c:when test="${empty startTime}">
                       value="<mt:DateNowTag/> 00:00"
                   </c:when>
                   <c:otherwise>
                        value="${startTime}"
                   </c:otherwise>
                 </c:choose>  value="${startTime}" style="width: 250px;" min="<mt:DateNowTag/> 00:00" style="width: 165px;" required/></td>
                 <td><input class="form-control" type="datetime-local" value="${endTime}" style="width: 250px;" name="endTime" min="<mt:DateNowTag/> 00:00" style="width: 165px;" required/></td>
                 <td><input type="submit" class="btn btn-info" value="${phrases['langConfirm']}"/></td>
            </tr>
        </table>
        </form>
        <br><br>
        <c:if test="${shipsList!=null}">
          <form action ="../admin/add_cruise" method ="post">
                     <div align="center" >
                   <table>
                   <th ><h6 style="color:#00ffff" align="center"/> ${phrases['langPickAShip']} </th>
                   <th ><h6 style="color:#00ffff" align="center"/> ${phrases['langPickRoute']}</th>
                   <th ><h6 style="color:#00ffff" align="center"/> ${phrases['langEnterDescription']}</th>
                   <th ><h6 style="color:#00ffff" align="center"/> ${phrases['langEnterCruiseName']}</th>
                   <th ><h6 style="color:#00ffff" align="center"/> ${phrases['langEnterPrice']}</th>
                   <tr>
                    <td> <select class="btn btn-dark dropdown-toggle" name="shipId" required style=" height: 80px">
                         <option disabled>${phrases['langPick']}</option>
                              <c:forEach items="${shipsList}" var="i">
                                 <option value="${i.getId()}">${i.getName()}</option>
                              </c:forEach></select></td>
                    <td> <select class="btn btn-dark dropdown-toggle" name="routeId" required style=" height: 80px">
                         <option disabled>${phrases['langPick']}</option>
                              <c:forEach items="${routeList}" var="i">
                                 <option value="${i.getId()}">${i.getName()}</option>
                              </c:forEach></select></td>
                     <td><textarea name="description" placeholder="langEnterDescription" class="form-control" style="width: 300px; height: 80px"></textarea></td>
                     <td><input name="cruiseName" class="btn btn-light" style="height: 80px" placeholder="${phrases['langEnterNameOfCruise']} " pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ\- ]{2,32}" required/></td>
                     <td><input name="price" type="number" placeholder="$" style="height: 80px; width: 100px;" class="btn btn-light" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}"  required/></td>
                     <td><input type="submit" class="btn btn-info" style="height: 80px" value="${phrases['langAddCruise']}"/></td>
                            <input type="hidden" name="startTime" value="${startTime}"/>
                            <input type="hidden" name="endTime" value="${endTime}"/>
                     </div>
                     </form>
                     </tr>
                   </table>
        </c:if>
           <br>
           <h2 style="color:#B22222" align="center">
               <c:if  test="${not empty error_message}" >${error_message}</c:if>
           </h2>
           <h4 style="color:#B22222" align="center">
              <c:forEach items="${errors}" var="i">
                 ${i}<br>
              </c:forEach>
           </h4>
                <footer style=" position: absolute; top: 90%; width: 100%; color:#fff">
                <hr> <p align="center" >© 2023 Oceania Cruises</p>
                </footer>
    </body>
</html>