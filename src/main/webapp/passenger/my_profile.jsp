<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Buy a Ticket
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000}
                </style>
    <body>
    <%@ include file="../header_passenger.jspf" %>
       <h2  style="color:#fff" align="center">${phrases['langMyTickets']}</h2>
           <table class="table table-dark table-striped">
                <th style="color:#0000ff"><h4 align="center"/> ${phrases['langCruiseName']} &nbsp</th>
                <th style="color:#0000ff"><h4 align="center"/> ${phrases['langTotalPrice']} &nbsp</th>
                <th style="color:#0000ff"><h4 align="center"/> ${phrases['langAmount']} &nbsp</th>
                <th style="color:#0000ff"><h4 align="center"/> ${phrases['langDuration']} &nbsp</th>
                <th style="color:#0000ff"><h4 align="center"/> ${phrases['langStatus']} &nbsp</th>
                     <c:forEach items="${my_tickets_list}" var="i">
                         <tr align="center">
                            <td style="color:#fff" ><h3/>${i.getCruiseName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getTotalPrice()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getNumberOfPassengers()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getDuration()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getStatus()} &nbsp</td>
                         </tr>
                     </c:forEach>
           </table>
    </body>
</html>
