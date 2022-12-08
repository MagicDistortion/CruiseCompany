<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Contacts
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
               <style>
                    body {background:#000000}
               </style>
    <body>
	 <%@ include file="header.jspf" %>
	 <br><br><br><br>
        <div style="color:#fff"/>
        <c:if test="${lang=='UA'}">
            <h2 align="center">Наші контакти</h2>
            <h2 align="center"> Ви завжди можете зв`язатись з нами по телефону +380(66)225-20-39</h2>
            <h2 align="center"> або за електронною поштою magicdistortion@gmail.com</h2>
        </c:if>
        <c:if test="${lang=='EN'}">
            <h2 align="center">Our Contacts</h2>
            <h2 align="center"> You can always contact Us by phone+380(66)225-20-39</h2>
            <h2 align="center"> or by e-mail magicdistortion@gmail.com</h2>
        </c:if>
        </div>
    </body>
</html>