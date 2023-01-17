<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Cruises
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
       <style>
            body {
            background:#808080;
            background-image: url(images/cruises.jpeg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
               }
       </style>
    <body>
       <%@ include file="header.jspf" %>
       <br>
           <div align="center" >
           <h2 style="color:#000000;background:#fff;width: 100px;">${phrases['langCruises']}</h2>
           <br>
               <form action ="cruises_list" method ="get">
              <nobr style="background:#fff; color:#000000"> ${phrases['langPagination']}
                <input class="btn btn-dark" type="text" name="pagination" size="3" pattern="[1-9]\d*"
                <c:choose>
                   <c:when test="${not empty pagination}">value="${pagination}"
                   </c:when>
                   <c:otherwise>value="5"
                   </c:otherwise>
               </c:choose>
                 placeholder="pagination" required>${phrases['langSorting']}</input>
                 </nobr>
                   <select type="button" class="btn btn-light dropdown-toggle" name="sort">
                      <option disabled>${phrases['langSorted']} &nbsp</option>
                      <option value="name" <c:if test="${sort.equals('name')}"> selected </c:if> >${phrases['langByCruiseName']}</option>
                      <option value="price" <c:if test="${sort.equals('price')}"> selected </c:if>>${phrases['langByPrice']}</option>
                      <option value="start_time" <c:if test="${sort.equals('start_time')}"> selected </c:if>>${phrases['langByStartDate']}</option>
                      <option value="duration" <c:if test="${sort.equals('duration')}"> selected </c:if>>${phrases['langByDuration']}</option>
                   </select>
                   <input type="submit" class="btn btn-info" value="${phrases['langGetThem']}"/><br>
               </form>
           <h2 style="color:#B22222">
           <table class="table table-hover">
               <th style="color:#00ffff"><h4/> ${phrases['langCruiseName']}</th>
               <th style="color:#00ffff"><h4/> ${phrases['langShipName']}</th>
               <th style="color:#00ffff"><h4/> ${phrases['langStartTime']}</th>
               <th style="color:#00ffff"><h4/> ${phrases['langEndTime']}</th>
               <th style="color:#00ffff"><h4/> ${phrases['langNumberOfPorts']}</th>
               <th style="color:#00ffff"><h4/> ${phrases['langDuration']}</th>
               <th style="color:#00ffff"><h4/> ${phrases['langPrice']}</th>
               <th style="color:#00ffff"><h4/> &nbsp</th>
                     <c:forEach items="${cruisesList}" var="i">
                         <tr>
                            <td style="color:#fff"><h3/>${i.getCruiseName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getShipName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getStartTimeString()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getEndTimeString()} &nbsp</td>
                            <td style="color:#fff" align="center"><h3/>${i.getNumberOfPorts()} &nbsp</td>
                            <td style="color:#fff" align="center"><h3/>${i.getDuration()}${phrases['langDays']}  &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getPrice()}$ &nbsp</td>
                            <td>
                            <form action ="cruise_details" method ="get">
                            <input type="hidden" name="cruiseId" value="${i.getId()}"/>
                            <input type="submit" class="btn btn-info" value="${phrases['langDetails']}">
                            </form>
                            </td>
                         </tr>
                     </c:forEach>
           </table>
                      <c:if test="${page>1}">
                      <a class="btn btn-outline-info"  href="cruises_list?pagination=${pagination}&sort=${sort}&page=${page-1}"><<</a>
                      </c:if>
                     <c:forEach begin="1" end="${pages}" var="i" step="1">
                          <c:choose>
                           <c:when test="${page==i}">
                            <a class="btn btn-info" href="cruises_list?pagination=${pagination}&sort=${sort}&page=${i}">${i}</a>
                           </c:when>
                           <c:otherwise>
                            <a class="btn btn-outline-info" href="cruises_list?pagination=${pagination}&sort=${sort}&page=${i}">${i}</a>
                           </c:otherwise>
                          </c:choose>
                      </c:forEach>
                      <c:if test="${page<pages}">
                      <a class="btn btn-outline-info" href="cruises_list?pagination=${pagination}&sort=${sort}&page=${page+1}">>></a>
                      </c:if>
           </div>
        </h2>
       </body>
</html>