<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Buy a Ticket
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000}
                </style>
    <body>
    <%@ include file="../header_passenger.jspf" %>
<br>

       <table align="center">
       <h2  style="color:#fff" align="center">${phrases['langBuyATicket']}</h2>
       <tr>
       <td> <select class="btn btn-primary dropdown-toggle" placeholder="open" id="sortBy" required>
                        <option disabled>${phrases['langPick']}</option>
                        <option value="date" <c:if test="${sortBy.equals('date')}"> selected </c:if>> ${phrases['langByDate']} </option>
                        <option value="duration" <c:if test="${sortBy.equals('duration')}"> selected </c:if> > ${phrases['langByDuration']} </option>
            </select>
       </td>

   <td > <form action ="../passenger/getCruises" id="sortByDate"  method ="post">
              <input class="btn btn-light" type="date"
              <c:choose>
                <c:when test="${empty date}">
                    value=<mt:myTag/>
                </c:when>
                <c:otherwise>
                     value="${date}"
                </c:otherwise>
              </c:choose>
                name="date" style="width: 150px;" required/>
              <input hidden name="sortBy" value="date"/>
              <input type="submit" class="btn btn-warning" value="${phrases['langPick']}"/>
            </form></td>

        <td > <form action ="../passenger/getCruises" id="sortByDuration" method ="post" style="display:none" align="center">
             <input name="duration" type="number" <c:if test="${duration!=null}"> value="${duration}" </c:if> value="1" class="btn btn-light" style="width: 150px;" required/>
             <input hidden name="sortBy" value="duration"/>
             <input type="submit" class="btn btn-warning" value="${phrases['langPick']}"/>
             </form></td>
        </table>
        <br>
       <c:if test="${cruisesList!=null && cruisesList.size()!=0}">
           <table class="table table-dark table-striped">
               <th style="color:#0000ff"><h4/> ${phrases['langCruiseName']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langStartDate']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langEndDate']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langNumberOfPorts']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langDuration']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langPrice']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langAmount']} &nbsp</th>
               <th style="color:#0000ff"><h4/> ${phrases['langBuy']} &nbsp</th>

                     <c:forEach items="${cruisesList}" var="i">
                         <tr><form action ="../passenger/buy_a_ticket" method ="post">
                            <td style="color:#fff"><h3/>${i.getCruiseName()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getStartTimeString()} &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getEndTimeString()} &nbsp</td>
                            <td style="color:#fff" align="center"><h3/>${i.getNumberOfPorts()} &nbsp</td>
                            <td style="color:#fff" align="center"><h3/>${i.getDuration()}${phrases['langDays']}  &nbsp</td>
                            <td style="color:#fff"><h3/>${i.getPrice()}$ &nbsp</td>
                            <td><input class="form-control" type="number" value="1" name="amount" style="width: 80px;" required/></td>
                            <input type="hidden" name="cruiseId" value="${i.getId()}"/>
                            <td><input type="submit" class="btn btn-warning" value="${phrases['langBuy']}"/></td>
                            </form>
                         </tr>
                     </c:forEach>
           </table>
       </c:if>
           <h2 style="color:#B22222" align="center">
              <c:if  test="${not empty error_message}" >${error_message}</c:if>
           </h2>

            <script type="text/javascript" >
               const sortBy = document.getElementById('sortBy')
               const sortByDate = document.getElementById('sortByDate')
               const sortByDuration = document.getElementById('sortByDuration')
               const onChange = () => {
                    if(sortBy.value=='date'){
                      sortByDate.style.display='block';
                      sortByDuration.style.display='none';
                    }
                    else {
                      sortByDate.style.display='none';
                      sortByDuration.style.display='block';
                    }
               }
               sortBy.addEventListener('change', onChange);
               onChange()
            </script>
    </body>
</html>
