<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Ships
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
               <style>
                    body {background:#000000}
               </style>
    <body>
       <%@ include file="header.jspf" %>
       <br>
           <div align="center" >
           <h2  style="color:#fff">${phrases['langShips']}</h2>
           <br>
               <form action ="ships_list" method ="get">
              <nobr style="color:#fff"> ${phrases['langPagination']}
                <input class="btn btn-dark" name="pagination" pattern="[1-9]\d*"
                <c:choose>
                   <c:when test="${not empty pagination}">value="${pagination}"
                   </c:when>
                   <c:otherwise>value="5"
                   </c:otherwise>
               </c:choose>
                 placeholder="pagination" required>${phrases['langSorting']}</input>
                 </nobr>
                   <select type="button" class="btn btn-dark dropdown-toggle" name="sort">
                      <option disabled>${phrases['langSorted']} &nbsp</option>
                      <option value="name" <c:if test="${sort.equals('name')}"> selected </c:if> >${phrases['langByShipName']}</option>
                      <option value="capacity" <c:if test="${sort.equals('capacity')}"> selected </c:if>>${phrases['langByCapacity']}</option>
                   </select>
                   <input type="submit" class="btn btn-dark" value="${phrases['langGetThem']}"/><br>
               </form>
           <h2 style="color:#B22222">
           <table class="table table-dark table-striped">
               <th style="color:#0000ff"><h4/> ${phrases['langShipName']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langCapacity']} &nbsp</th>
                     <c:forEach items="${shipsList}" var="i">
                         <tr>
                            <td style="color:#fff"><h3/>${i.getName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getCapacity()} &nbsp</td>
                         </tr>
                     </c:forEach>
           </table>
                      <c:if test="${page>1}">
                      <a class="btn btn-outline-primary"  href="ships_list?pagination=${pagination}&sort=${sort}&page=${page-1}"><<</a>
                      </c:if>
                     <c:forEach begin="1" end="${pages}" var="i" step="1">
                          <c:choose>
                           <c:when test="${page==i}">
                            <a class="btn btn-primary" href="ships_list?pagination=${pagination}&sort=${sort}&page=${i}">${i}</a>
                           </c:when>
                           <c:otherwise>
                            <a class="btn btn-outline-primary" href="ships_list?pagination=${pagination}&sort=${sort}&page=${i}">${i}</a>
                           </c:otherwise>
                          </c:choose>
                      </c:forEach>
                      <c:if test="${page<pages}">
                      <a class="btn btn-outline-primary" href="ships_list?pagination=${pagination}&sort=${sort}&page=${page+1}">>></a>
                      </c:if>
           </div>
        </h2>
       </body>
</html>