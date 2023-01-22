<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>My cruises
		</title>
           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(../images/my_cruises.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <%@ include file="../header_staff.jspf" %>
    <br>
       <h2 style="color:#fff" align="center">${phrases['langMyCruises']}</h2>
         <table class="table table-hover">
              <th style="color:#00ffff"><h4/> ${phrases['langCruiseName']}</th>
              <th style="color:#00ffff"><h4/> ${phrases['langStartTime']}</th>
              <th style="color:#00ffff"><h4/> ${phrases['langEndTime']}</th>
              <th style="color:#00ffff"><h4/> ${phrases['langNumberOfPorts']}</th>
              <th style="color:#00ffff"><h4/> ${phrases['langDuration']}</th>
              <th style="color:#00ffff"><h4/> &nbsp</th>
                <c:forEach items="${cruisesList}" var="i">
                    <tr>
                       <td style="color:#fff"><h3/>${i.getCruiseName()} &nbsp</td>
                       <td style="color:#fff"><h3/>${i.getStartTimeString()} &nbsp</td>
                       <td style="color:#000000"><h3/>${i.getEndTimeString()} &nbsp</td>
                       <td style="color:#fff" align="center"><h3/>${i.getNumberOfPorts()} &nbsp</td>
                       <td style="color:#fff" align="center"><h3/>${i.getDuration()}${phrases['langDays']}  &nbsp</td>
                    </tr>
                </c:forEach>
          </table><br>
              <h2 style="color:#B22222" align="center">
                <c:if  test="${not empty error_message}" >${error_message}</c:if>
              </h2>
        <footer style=" position: absolute; top: 90%; width: 100%; color:#fff">
        <hr> <p align="center" >© 2023 Oceania Cruises</p>
        </footer>
    </body>
</html>
