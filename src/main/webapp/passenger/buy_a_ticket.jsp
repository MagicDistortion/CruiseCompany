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
<br>
       <table align="center">
       <tr>

        <td> <form action ="../passenger/getCruises" method ="post" align="center">
              <input class="form-control-sm" type="date" value="${date}" name="date" required/>
              <input type="submit" class="btn btn-warning" value="${phrases['langByDate']}"/>&nbsp &nbsp &nbsp &nbsp
            </form></td>

        <td> <form action ="../passenger/getCruises" method ="post" align="center">
             <input name="duration" type="number" value="${duration}" class="btn btn-light" placeholder="${phrases['langEnterDuration']} " required/>
             <input type="submit" class="btn btn-warning" value="${phrases['langByDuration']}"/>
             </form></td>
        </table>
        <br>
       <c:if test="${cruisesList!=null && cruisesList.size()!=0}">
           <table class="table table-dark table-striped">
               <th style="color:#0000ff"><h4/> ${phrases['langCruiseName']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langStartDate']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langEndDate']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langNumberOfPorts']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langDuration']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langPrice']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langBuy']} &nbsp</th>

                     <c:forEach items="${cruisesList}" var="i">
                         <tr>
                            <td style="color:#fff"><h3/>${i.getCruiseName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getStartTimeString()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getEndTimeString()} &nbsp</td>
                            <td style="color:#fff"  align="center"><h3/>${i.getNumberOfPorts()} &nbsp</td>
                            <td style="color:#fff"  align="center"><h3/>${i.getDuration()}${phrases['langDays']}  &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getPrice()}$ &nbsp</td>
                            <td><form action ="../passenger/buy_a_ticket" method ="post">
                              <input type="hidden" name="id" value="${i.getId()}"/>
                              <input type="submit" class="btn btn-warning" value="${phrases['langBuy']}"/></td>
                         </tr>
                     </c:forEach>
           </table>
       </c:if>
           <h2 style="color:#B22222" align="center">
              <c:if  test="${not empty error_message}" >${error_message}</c:if>
           </h2>

    </body>
</html>
