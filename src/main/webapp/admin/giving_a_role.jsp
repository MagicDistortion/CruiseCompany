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
                   body {background:#000000}
                </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
<br><br>
    <div align="center" >
        <h2 style="color:#fff">${phrases['langGivingARole']}</h2>
            <form action ="../admin/users_list" method ="get">
                <input type="submit" class="btn btn-dark" value="${phrases['langGetUsers']}"/><br>
            </form>
        <h2 style="color:#B22222">
         <table class="table table-dark table-striped table-bordered">
            <th style="color:#0000ff"><h4/> ${phrases['langSurname']} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${phrases['langName']} &nbsp </th>
            <th style="color:#0000ff"><h4/> ${phrases['langPickARole']} &nbsp</th>
                  <c:forEach items="${userList}" var="i">
                      <tr>
                         <td style="color:#fff"><h4/>${i.getSurname()} &nbsp</td>
                         <td style="color:#fff"><h4/>${i.getName()} &nbsp</td>
                         <td ><h4/>
                         <form action ="../admin/give_a_role" method ="post">
                            <input type="hidden" name="userId" value="${i.getId()}"/>
                             <select class="btn btn-info dropdown-toggle" name="role">
                                 <option value="1">${phrases['langPassenger']}</option>
                                 <option value="2">${phrases['langAdmin']}</option>
                                 <option value="3">${phrases['langStaff']}</option>
                             </select>
                             <input type="submit" class="btn btn-warning" value="${phrases['langPick']}"/>
                         </form>
                         </td>
                      </tr>
                  </c:forEach>
        </table>
                     <h2 style="color:#B22222">
                          <c:if  test="${not empty error_message}" >${error_message}</c:if>
                     </h2>
        </h2>
    </div>
    </body>
</html>
