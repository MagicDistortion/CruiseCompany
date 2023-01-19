<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>My profile
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(../images/my_profile.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <%@ include file="../header_passenger.jspf" %>
       <h2 style="color:#fff" align="center">${phrases['langMyTickets']}</h2>
           <table class="table table-hover">
                <th style="color:#00ffff"><h4 align="center"/> ${phrases['langCruiseName']} &nbsp</th>
                <th style="color:#00ffff"><h4 align="center"/> ${phrases['langAmount']} &nbsp</th>
                <th style="color:#00ffff"><h4 align="center"/> ${phrases['langDuration']} &nbsp</th>
                <th style="color:#00ffff"><h4 align="center"/> ${phrases['langTotalPrice']} &nbsp</th>
                <th style="color:#00ffff"><h4 align="center"/> ${phrases['langStatus']} &nbsp</th>
                <th/>
                     <c:forEach items="${my_tickets_list}" var="i">
                         <tr align="center">
                            <td style="color:#fff" ><h3/>${i.getCruiseName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getNumberOfPassengers()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getDuration()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getTotalPrice()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getStatus()} &nbsp</td>
                            <c:if test="${i.getStatus().equals('not paid')}">
                                <form action ="../passenger/pay_for_ticket" method ="post">
                                <input type="hidden" name="ticket_id" value="${i.getId()}"/>
                                <td> <input
                                  <c:if test="${user.getMoney()<i.getTotalPrice()}">disabled type="submit" class="btn btn-secondary"</c:if>
                                 type="submit" class="btn btn-info" value="${phrases['langPay']}"/></td>
                                </form>
                            </c:if>
                            <c:if test="${!i.getStatus().equals('not paid')}">
                            <td/>
                            </c:if>
                         </tr>
                     </c:forEach>
           </table>
           <br><br><br><br><br><br>


            <footer align="left" style="color:#fff">
            <hr>
                   <h4 style="color:#fff">${phrases['langMyMoney']} = ${user.getMoney()}$</h4>
                   <form action ="../passenger/put_on_the_account" method ="post">
                   <input name="deposit" type="number" class="btn btn-light" placeholder="${phrases['langPutOnTheAccount']} " style="width: 200px;" required/>
                   <input type="submit" class="btn btn-info" value="${phrases['langDeposit']}"/>
                   </form>
                <p align="center">© 2023 Cruise Company</p>
            </footer>
    </body>
</html>
