<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Cruise Company
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
    <body>
       <style>
            body {
            background:#808080;
            background-image: url(images/index.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <%@ include file="header.jspf" %>
        <c:if test="${empty user}">
           <br><br><br><br><br>
               <form id="login" action="login" method="post">
                   <div align="center" >
                        <table >
                            <tr>
	                          <td><h3 style="color:#fff"/>${phrases['langLogin']}:</td>
	                          <td><input  name="login" class="form-control" placeholder="${phrases['langEnterLogin']}" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{2,32}" required/></td>
                            </tr>
                            <tr>
    	                      <td><h3 style="color:#fff"/>${phrases['langPassword']}:</td>
	                          <td><input type="password" class="form-control" name="password" placeholder="${phrases['langEnterPassword']}" pattern="^[0-9A-Za-zА-Яа-яІіЇїєЄ]{4,32}" required/></td>
	                        </tr>
	                        <tr><td></td>
	                          <td><div class="g-recaptcha" data-sitekey="6LcnqxkkAAAAAEiO0dh5hcpAmxy-sgibiy3hbbAx" required></div></td>
	                          </tr><td></td>
	                          <td><h5><div class="text-danger" id="error"></h5></div></td>
	                          </tr>
	                        </td>
                        </table>
    	                <input type="submit" class="btn btn-info" value=${phrases['langGo']} /><br>
                           <h2 style="color:#B22222">
                             <c:if  test="${not empty error_message}" >${error_message}</c:if>
                           </h2>
                   </div>
               </form>
        </c:if>
                <footer style=" position: absolute; top: 90%; width: 100%; color:#fff">
                <hr> <p align="center" >© 2023 Oceania Cruises</p>
                </footer>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script>
        window.onload = function(){
            const form = document.getElementById("login");
            const error = document.getElementById("error");

            form.addEventListener("submit",function(event){
                event.preventDefault();
                const response = grecaptcha.getResponse();
                if(response){
                    form.submit();
                }else{
                    error.innerHTML= "*Пройдіть перевірку капчою";
                }
            });
        }
        </script>

  </body>
</html>

