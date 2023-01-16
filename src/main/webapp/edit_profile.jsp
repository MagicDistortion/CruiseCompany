<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
     <meta charset="UTF-8">
     <title>Edit my profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>

    <body>
       <style>
            body {
            background:#808080;
            background-image: url(images/edit_profile.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
        <br><br><br><br><br><br>

                <form action ="edit_profile" method ="post">
                    <div align="center" >
                     <table>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langSurname']}:</h3></td>
                         <td><input class="form-control" name="surname" value="${user.getSurname()}" pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{2,32}" /></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langName']}:</h3></td>
                         <td><input class="form-control" name="name" value="${user.getName()}" pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{2,32}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langLogin']}:</h3></td>
	                     <td><input class="form-control" name="login" value="${user.getLogin()}" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}" ></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langNewPassword']}:</h3></td>
                         <td><input class="form-control" type="password" placeholder="${phrases['langNewPassword']}" name="password" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{4,32}" /></td>
                       </tr>
	                   <tr>
	                     <td style="color:#0000ff"/><h3>${phrases['langRePassword']}:</h3></td>
	                     <td><input class="form-control" type="password" placeholder="${phrases['langRePassword']}" name="repassword" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{4,32}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langTel']}:</h3></td>
                         <td> <input class="form-control" name="tel" value="${user.getTel()}" pattern="^[0-9]{9,12}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langDateOfBirth']}</h3></td>
                         <td><input class="form-control" type="date" name="date_of_birth" value="${user.getDateOfBirth()}" /></td>
                       </tr>
                      </table>
                    	<input type="submit" class="btn btn-dark" value="${phrases['langEditNow']}"/><br>
                           <h2 style="color:#B22222">
                               <c:forEach items="${errors}" var="i">
                                  ${i}<br>
                                  </c:forEach>
                                <a class=" btn btn-light" href="index.jsp">${phrases['langBackToMain']}</a>
                           </h2>
                    </div>
                </form>
    </body>
</html>