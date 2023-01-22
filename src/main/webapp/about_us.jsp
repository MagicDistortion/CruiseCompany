<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>About Us
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(images/about_us.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
    <%@ include file="header.jspf" %>

        <div style="color:#fff"/>
        <c:if test="${lang=='UA'}">
            <h2 align="center">Про Нас</h2><br>
            <h2 align="center">Морські круїзи Oceania Cruises створені для туристів,<br> які цінують спокійний та помірний відпочинок,<br> що знаходять час для розслаблення та милування красою чужих міст.<br>
                               Здійснюються круїзи Чорним і Середземним морями,<br> портами Карибського басейну і Майамі.<br> У програмі є цікаві екскурсії на кожен день плавання.</h2>
        </c:if>
        <c:if test="${lang=='EN'}">
            <h2 align="center">About us</h2><br>
            <h2 align="center">Sea cruises Oceania Cruises are designed for tourists <br> who appreciate a calm and moderate vacation,<br> finding time to relax and admire the beauty of foreign cities.<br>
                               There are cruises on the Black and Mediterranean Seas,<br> the ports of the Caribbean and Miami.<br> The program includes interesting excursions for every day of sailing. </h2>
        </c:if>
        </div>
                <footer style=" position: absolute; top: 90%; width: 100%; color:#fff">
                <hr> <p align="center" >© 2023 Oceania Cruises</p>
                </footer>
    </body>
</html>