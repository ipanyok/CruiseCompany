<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<title>Cruise Company</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/w3.css">
<style>
    body, h1, h5 {
        font-family: "Raleway", sans-serif
    }

    body, html {
        height: 100%
    }

    .bgimg {
        background-image: url('/images/gallery-1.jpg');
        min-height: 100%;
        background-position: center;
        background-size: cover;
    }
</style>
<body>

<div class="bgimg w3-display-container w3-text-white">
    <div class="w3-display-middle">
        <c:if test="${buckets != null}">
            <form name="tablebuck" action="cruisecompany" method="post">
                <input type="hidden" name="command" value="ordButton">
                <p align="center">
                <table border="1" class="colortext">
                    <thead>
                    <tr>
                        <th>${ticketLoc}</th>
                        <th>${cruiseNameLoc}</th>
                        <th>${shipLoc}</th>
                        <th>${cFromLoc}</th>
                        <th>${cToLoc}</th>
                        <th>${categoryLoc}</th>
                        <th>${excLoc}</th>
                        <th>${priceLoc}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${buckets}" var="buf">
                        <tr>
                            <td><c:out value="${buf.ticket}"/></td>
                            <td><c:out value="${buf.cruise.name}"/></td>
                            <td><c:out value="${buf.ship}"/></td>
                            <td><c:out value="${buf.countryFrom} (${buf.cityFrom})"/></td>
                            <td><c:out value="${buf.countryTo} (${buf.cityTo})"/></td>
                            <td><c:out value="${buf.cruise.category}"/></td>
                            <td>
                                <c:if test="${buf.excursion != null}">
                                    <select name="excursionList${buf.cruise.id}">
                                        <option></option>
                                        <c:forEach items="${buf.excursion}" var="ex">
                                            <option>${ex.name}, price: ${ex.price}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </td>
                            <td><c:out value="${buf.price}"/></td>
                            <input type="hidden" name="idBucketBtn${buf.cruise.id}" value="${buf.bucket.id}">
                            <%--<td><input class="w3-bar-item w3-button" type="submit" name="buyNow" value="${buyNowLoc}${buf.cruise.id}"></td>--%>
                           <td> <button  class="w3-bar-item w3-button" name="buyNow" value="${buyNowLoc}${buf.cruise.id}" >${buyNowLoc}</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </p>
            </form>
        </c:if>
    </div>
</div>

<div class="w3-top w3-hide-small">
    <div class="w3-bar w3-xlarge w3-black w3-opacity w3-hover-opacity-off" id="myNavbar">
        <a href="/cruisecompany?command=homeButton&homeBtn=home" class="w3-bar-item w3-button">${homeMainLoc}</a>
        <a href="/cruisecompany?command=bucketButton&bucketBtn=bucket" class="w3-bar-item w3-button">${bucketMainLoc}</a>
        <a href="/cruisecompany?command=orderButton&orderBtn=order" class="w3-bar-item w3-button">${orderMainLoc}</a>
        <a href="/cruisecompany?command=logoutButton&logoutBtn=logout" class="w3-bar-item w3-button">${logoutMainLoc}</a>
        <label class="w3-bar-item w3-button">&nbsp;|&nbsp;</label>
        <h6><p align="right"> Hello, ${user}&nbsp;</p></h6>
    </div>
</div>
</body>
</html>



