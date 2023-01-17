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
             <form action ="../admin/add_cruise" method ="post">
             <div align="center" >
<table class="table table-hover" >
           <tr>
             <td style="color:#0000ff; width: 1000px;"/><h3>${phrases['langPickAShip']}</h3></td>
             <td> <select class="btn btn-light dropdown-toggle" style="width: 250px;" name="shipId" required>
                  <option disabled>${phrases['langPick']}</option>
                   <c:forEach items="${shipsList}" var="i">
                      <option value="${i.getId()}">${i.getName()}</option>
                   </c:forEach></select></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langEnterDescription']}</h3></td>
             <td><div class="form-floating">
                <textarea name="description" class="form-control" style="width: 1000px; height: 100px"></textarea>
              </div></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langEnterRoute']}</h3></td>
             <td><div class="form-floating">
                <textarea name="route" class="form-control" style="width: 1000px; height: 100px"></textarea>
              </div></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langEnterCruiseName']}</h3></td>
             <td><input name="cruiseName" class="btn btn-light" style="width: 250px;" placeholder="${phrases['langEnterNameOfCruise']} " pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ\- ]{2,32}" required/></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langEnterNumberOfPorts']}</h3></td>
             <td><input name="numberOfPorts" class="btn btn-light" style="width: 250px;" placeholder="${phrases['langEnterNumberOfPorts']} " pattern="[0-9]\d*"  size="25" required/></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langEnterPrice']}</h3></td>
             <td><input name="price" type="number" placeholder="$" style="width: 250px;" class="btn btn-light" style="width: 100px;" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}"  required/></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langPickStartTime']}</h3></td>
             <td><input class="form-control" type="datetime-local" value="${timeNow}" style="width: 250px;" name="startTime" style="width: 165px;" required/></td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langPickEndTime']}</h3></td>
             <td><input class="form-control" type="datetime-local" value="${timePlusDay}" style="width: 250px;" name="endTime" style="width: 165px;" required/></td>
           </tr>
           </table>
            <input type="submit" class="btn btn-info"  value="${phrases['langAddCruise']}"/>
         </table>






             </form>
           <br>
           <h4 style="color:#B22222" align="center">
              <c:forEach items="${errors}" var="i">
                 ${i}<br>
              </c:forEach>
           </h4>
    </body>
</html>