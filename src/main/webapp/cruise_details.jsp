<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Cruise Details
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(images/cruise_details.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <br>
         <table class="table table-hover" >
           <tr>
             <td style="color:#0000ff; width: 1000px;"/><h3>${phrases['langCruiseName']}:</h3></td>
             <td style="color:#000000; width: 4000px;"/><h3>${cruise.getCruiseName()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langShipName']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getShipName()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langDescription']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getDescription()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langRoute']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getRoute()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langStartTime']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getStartTimeString()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langEndTime']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getEndTimeString()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langDuration']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getDuration()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langPrice']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getPrice()}</h3> </td>
           </tr>
           <tr>
             <td style="color:#0000ff"/><h3>${phrases['langNumberOfPorts']}:</h3></td>
             <td style="color:#000000"/><h3>${cruise.getNumberOfPorts()}</h3> </td>
           </tr>
         </table>
            <div align="center"/> <a class=" btn btn-light" href="cruises_list">${phrases['langBackCruises']}</a>
       </body>
               <footer align="center"style="color:#fff">
               <hr>
                   <p>© 2023 Cruise Company</p>
               </footer>
</html>