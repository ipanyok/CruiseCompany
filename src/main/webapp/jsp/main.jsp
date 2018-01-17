<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cruise App</title>
    <style>
        body {
            background: url('/images/gallery-1.jpg');
            min-height: 100%;
            background-position: center;
            background-size: cover;
        }

        .colortext {
            background-color: inherit;
            color: white;
        }

        .colortextlabel {
            color: white;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/w3.css">
</head>
<body>
<div class="w3-top w3-hide-small">
    <div class="w3-bar w3-xlarge w3-black w3-opacity w3-hover-opacity-off" id="myNavbar">
        <a href="/cruisecompany?command=homeButton&homeBtn=home" class="w3-bar-item w3-button">${homeMainLoc}</a>
        <a href="/cruisecompany?command=bucketButton&bucketBtn=bucket" class="w3-bar-item w3-button">${bucketMainLoc}</a>
        <a href="/cruisecompany?command=orderButton&orderBtn=order" class="w3-bar-item w3-button">${orderMainLoc}</a>
        <a href="/cruisecompany?command=logoutButton&logoutBtn=logout" class="w3-bar-item w3-button">${logoutMainLoc}</a>
        <label class="w3-bar-item w3-button">&nbsp;|&nbsp;</label>
        <a href="/cruisecompany?command=enMainLocation&enMainLocBtn=english" class="w3-bar-item w3-button">EN</a>
        <a href="/cruisecompany?command=uaMainLocation&uaMainLocBtn=ukraine" class="w3-bar-item w3-button">UA</a>
        <h6><p align="right"> Hello, ${user}&nbsp;</p></h6>
    </div>
</div>
<br/>
<br/><br/><br/><br/>

<form name="find" action="cruisecompany" method="post">
    <input type="hidden" name="command" value="findButton"/>
    <label class="colortextlabel">${countryFromLoc}&nbsp;</label>
    <select name="countryFromList">
        <option></option>
        <c:forEach items="${countryFrom}" var="bufFrom">
            <option>${bufFrom.name}</option>
        </c:forEach>
    </select>
    <label class="colortextlabel">${countryToLoc}&nbsp;</label>
    <select name="countryToList">
        <option></option>
        <c:forEach items="${countryTo}" var="bufTo">
            <option>${bufTo.name}</option>
        </c:forEach>
    </select>
    <label class="colortextlabel">${categoryLoc}&nbsp;</label>
    <select name="category">
        <option></option>
        <option>econom</option>
        <option>business</option>
        <option>vip</option>
    </select>
    <label class="colortextlabel">${dateLoc}&nbsp;</label>
    <input name="date" type="text" value="${dateValue}"/>
    <input type="submit" value="${findLoc}" name="findBtn"/>
</form>

<form name="table" action="cruisecompany" style="visibility:${isHidden}">
    <p align="center">
    <table border="1" class="colortext">
        <thead>
        <tr>
            <th>${cruiseNameLoc}</th>
            <th>${shipLoc}</th>
            <th>${cFromLoc}</th>
            <th>${cToLoc}</th>
            <th>${startDateLoc}</th>
            <th>${finishDateLoc}</th>
            <th>${portsLoc}</th>
            <th>${categoryLoc}</th>
            <th>${priceLoc}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cruises}" var="item">
            <tr>
                <td><c:out value="${item.cruise.name}"/></td>
                <td><c:out value="${item.ship}"/></td>
                <td><c:out value="${item.countryFrom} (${item.cityFrom})"/></td>
                <td><c:out value="${item.countryTo} (${item.cityTo})"/></td>
                <td><c:out value="${item.cruise.dateStart}"/></td>
                <td><c:out value="${item.cruise.dateFinish}"/></td>
                <td><c:out value="${item.cruise.portsCount}"/></td>
                <td><c:out value="${item.cruise.category}"/></td>
                <td><c:out value="${item.cruise.price}"/></td>
                <td><a href="/cruisecompany?command=addButton&addBtn=ADD${item.cruise.id}"
                       class="w3-bar-item w3-button">${addLoc}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </p>

    <p align="center">
        <c:forEach items="${countPages}" var="elem">
            <a href="/cruisecompany?command=page&page=${elem.number}" class="colortextlabel">${elem.number}</a>
        </c:forEach>
    </p>

</form>
<p align="center"><label class="colortext"> <c:out value="${message}"/> </label></p>
<p align="center"><label class="colortext"> <c:out value="${messageOrder}"/> </label></p>
</body>
</html>