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
<form name="d" action="cruisecompany">
    <div class="w3-top w3-hide-small">
        <div class="w3-bar w3-xlarge w3-black w3-opacity w3-hover-opacity-off" id="myNavbar">
            <a href="/cruisecompany?command=homeButtonAdmin&homeBtnAdmin=homeAdmin" class="w3-bar-item w3-button">${homeMainLoc}</a>
            <a href="/cruisecompany?command=logoutButton&logoutBtn=logout" class="w3-bar-item w3-button" >${logoutMainLoc}</a>
            <label class="w3-bar-item w3-button">&nbsp;|&nbsp;</label>
            <a href="/cruisecompany?command=enAdminLocation&enAdminLocBtn=english" class="w3-bar-item w3-button">EN</a>
            <a href="/cruisecompany?command=uaAdminLocation&uaAdminLocBtn=ukraine" class="w3-bar-item w3-button">UA</a>
            <h6><p align="right"> Hello, ${user}&nbsp;</p></h6>
        </div>
    </div>
</form>
<div class="bgimg w3-display-container w3-text-white">
    <div class="w3-display-middle">
        <c:if test="${orders != null}">
            <form name="tableorderAdmin" action="cruisecompany" method="post">
                <input type="hidden" name="command" value="addBonus"/>
                <p align="center">
                <table border="1" class="colortext">
                    <thead>
                    <tr>
                        <th>${userLoc}</th>
                        <th>${cruiseNameLoc}</th>
                        <th>${shipLoc}</th>
                        <th>${cFromLoc}</th>
                        <th>${cToLoc}</th>
                        <th>${categoryLoc}</th>
                        <th>${excLoc}</th>
                        <th>${ticketLoc}</th>
                        <th>${priceLoc}</th>
                        <th>${bonusLoc}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="buf">
                        <tr>
                            <td><c:out value="${buf.user.login}"/></td>
                            <td><c:out value="${buf.cruise.name}"/></td>
                            <td><c:out value="${buf.ship.name}"/></td>
                            <td><c:out value="${buf.countryFrom.name} (${buf.countryFrom.city})"/></td>
                            <td><c:out value="${buf.countryTo.name} (${buf.countryTo.city})"/></td>
                            <td><c:out value="${buf.cruise.category}"/></td>
                            <td><c:out value="${buf.order.excursion}"/></td>
                            <td><c:out value="${buf.order.ticket}"/></td>
                            <td><c:out value="${buf.order.price}"/></td>
                            <td><input type="text" name="bonus${buf.order.id}" value="<c:out value="${buf.order.bonus}"/>"></td>
                            <td><input class="w3-bar-item w3-button" type="submit" name="addBonusBtn" value="${addBonusLoc}${buf.order.id}"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </p>
                <p align="center"><label class="colortext"> <c:out value="${messageUpdate}"/> </label></p>
            </form>
        </c:if>
    </div>
</div>

</body>
</html>