<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>

<body>
       <style>
            body {
            background:#808080;
            background-image: url(images/register.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
<br><br><br><br><br><br>

<form  action ="register" method ="post" >
<div align="center" >
    <table>
         <tr >
            <td style="color:#fff" /><h3/> ${phrases['langSurname']}:</td>
            <td><input class="form-control" name="surname" placeholder="${phrases['langEnterSurname']}" value="${user.getSurname()}" pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{2,32}" required/></td>
        </tr>
        <tr>
            <td style="color:#fff" /><h3/> ${phrases['langName']}:</td>
            <td><input class="form-control" name="name" placeholder="${phrases['langEnterName']}" value="${user.getName()}" pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{2,32}" required/></td>
        </tr>
        <tr>
            <td style="color:#fff"/><h3/>${phrases['langLogin']}:</td>
	        <td><input class="form-control" name="login" placeholder="${phrases['langEnterLogin']}" value="${user.getLogin()}" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}" required/></td>

        </tr>
        <tr>
            <td style="color:#fff"/><h3/>${phrases['langPassword']}:</td>
            <td><input class="form-control" type="password" name="password"  placeholder="${phrases['langEnterPassword']}" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{4,32}" required/></td>
        </tr>
	    <tr>
	        <td style="color:#fff"/><h3/>${phrases['langRePassword']}:</td>
	        <td><input class="form-control" type="password" name="repassword" placeholder="${phrases['langRePassword']}" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{4,32}" required/></td>

        </tr>
        <tr>
            <td style="color:#fff"/><h3/>${phrases['langTel']}:</td>
            <td> <input class="form-control" name="tel" value="${tel}" placeholder="${phrases['langEnterTel']}" pattern="^[0-9]{9,12}" required></td>
        </tr>
        <tr>
            <td style="color:#fff"/><h3/> ${phrases['langDateOfBirth']}:</td>
            <td><input class="form-control" type="date" name="date_of_birth" value="1900-01-01" required/></td>
        </tr>
    </table>
    	<input type="submit" class="btn btn-dark" value="${phrases['langRegister']}"/><br>

    <h2 style="color:#B22222">
         <c:forEach items="${errors}" var="i">
             ${i}<br>
         </c:forEach>
             <c:if  test="${not empty error_message}" >${error_message}</c:if>
    </h2>
     <a class=" btn btn-light" href="index.jsp">${phrases['langBackToMain']}</a>

</div>
</form>
<br><br><br><br>
            <footer align="center"style="color:#fff">
            <hr>
                <p>© 2023 Cruise Company</p>
            </footer>
</body>
</html>