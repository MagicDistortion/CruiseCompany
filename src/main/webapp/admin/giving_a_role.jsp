<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Giving a role
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(../images/giving_a_role.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
<br><br>
    <div align="center" >
        <table class="table table-hover ">
        <tr>
        <td>
        <h2 style="color:#fff" align="center">${phrases['langGivingARole']}</h2>
         <table class="table table-hover ">
            <th style="color:#00ffff"><h4/> ${phrases['langSurname']} &nbsp</th>
            <th style="color:#00ffff"><h4/> ${phrases['langName']} &nbsp </th>
            <th style="color:#00ffff"><h4/> ${phrases['langPickARole']} &nbsp</th>
                  <c:forEach items="${userList}" var="i">
                      <tr>
                         <td style="color:#fff"><h4/>${i.getSurname()} &nbsp</td>
                         <td style="color:#fff"><h4/>${i.getName()} &nbsp</td>
                         <td ><h4/>
                         <form action ="../admin/give_a_role" method ="post">
                            <input type="hidden" name="userId" value="${i.getId()}"/>
                             <select class="btn btn-light dropdown-toggle" name="role">
                                 <option value="1">${phrases['langPassenger']}</option>
                                 <option value="2">${phrases['langAdmin']}</option>
                                 <option value="3">${phrases['langStaff']}</option>
                             </select>
                             <input type="submit" class="btn btn-info" value="${phrases['langPick']}"/>
                         </form>
                         </td>
                      </tr>
                  </c:forEach>
        </table><br>
                             <h2 style="color:#B22222" align="center">
                                  <c:if  test="${not empty users_List_error}" >${users_List_error}</c:if>
                             </h2>
        </td>
        <td>
        <h2 style="color:#fff" align="center">${phrases['langAssignShip']}</h2>
         <table class="table table-hover ">
                    <th style="color:#00ffff"><h4/> ${phrases['langSurname']} &nbsp</th>
                    <th style="color:#00ffff"><h4/> ${phrases['langName']} &nbsp </th>
                    <th style="color:#00ffff"><h4/> ${phrases['langPickAShip']} &nbsp</th>
                    <th/>
                          <c:forEach items="${staffList}" var="i">
                              <tr>
                                 <td style="color:#fff"><h4/>${i.getSurname()} &nbsp</td>
                                 <td style="color:#fff"><h4/>${i.getName()} &nbsp</td>
                                 <td ><h4/>
                                 <form action ="../admin/assign_a_ship" method ="post">
                                    <input type="hidden" name="userId" value="${i.getId()}"/>
                                     <select class="btn btn-light dropdown-toggle" name="shipId">
                                           <c:forEach items="${shipsList}" var="i">
                                              <option value="${i.getId()}">${i.getName()}</option>
                                           </c:forEach></select></td>
                                     </select>
                                   <td><input type="submit" class="btn btn-info" value="${phrases['langPick']}"/></td>
                                 </form>
                                 </td>
                              </tr>
                          </c:forEach>
         </table></br>
                      <h2 style="color:#B22222" align="center">
                           <c:if  test="${not empty staff_List_error}" >${staff_List_error}</c:if>
                      </h2>
        </td></tr>
       </table>
    </div>
                <footer style=" position: absolute; top: 90%; width: 100%; color:#fff">
                <hr> <p align="center" >© 2023 Oceania Cruises</p>
                </footer>
    </body>
</html>
