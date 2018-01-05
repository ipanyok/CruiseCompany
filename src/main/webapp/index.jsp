<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1 class="w3-jumbo w3-animate-top">CRUISE COMPANY</h1>
        <hr class="w3-border-grey" style="margin:auto;width:40%">
        <p class="w3-large w3-center">never gonna be alone</p>
    </div>
    <div class="w3-display-bottomleft w3-padding-large">
        All Rights Reserved (c)
    </div>
</div>

<div class="w3-top w3-hide-small">
    <div class="w3-bar w3-xlarge w3-black w3-opacity w3-hover-opacity-off" id="myNavbar">
        <form name="mainformLog" action="cruisecompany">
            <a href="/cruisecompany?command=login&loginBtn=LOG+IN" class="w3-bar-item w3-button">
                <c:if test="${loginLoc == null}"><c:out value="LOG IN"></c:out></c:if>
                <c:if test="${loginLoc != null}"><c:out value="${loginLoc}"></c:out></c:if>
            </a>
            <a href="/cruisecompany?command=register&registerBtn=REGISTER" class="w3-bar-item w3-button">
                <c:if test="${registerLoc == null}"><c:out value="REGISTER"></c:out></c:if>
                <c:if test="${registerLoc != null}"><c:out value="${registerLoc}"></c:out></c:if>
            </a>
            <label class="w3-bar-item w3-button">&nbsp;|&nbsp;</label>
            <a href="/cruisecompany?command=enLocation&enLocBtn=english" class="w3-bar-item w3-button">EN</a>
            <a href="/cruisecompany?command=uaLocation&uaLocBtn=ukraine" class="w3-bar-item w3-button">UA</a>
        </form>
    </div>
</div>
</body>
</html>



