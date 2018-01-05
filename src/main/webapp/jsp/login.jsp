<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <style>
        .colortext {
            color: darkred;
        }
    </style>
    <link rel='stylesheet prefetch' href='/css/bootstrap.min.css'>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="wrapper">
    <form class="form-signin" name="loginForm" action="cruisecompany" method="POST">
        <input type="hidden" name="command" value="loginButton"/>
        <h2 class="form-signin-heading"><p align="center">${signUpLoc}</p></h2>
        ${logLoc}
        <br/>
        <input type="text" class="form-control" name="loginUser" value="${login}"/>
        ${passLoc}
        <br/>
        <input type="password" class="form-control" name="passwordUser" value="${pass}"/>
        <input class="btn btn-lg btn-primary btn-block" type="submit" name="loginBtn" value="${lLoc}" />
    </form>
</div>
<p align="center"><label class="colortext"> <c:out value="${message}"/> </label></p>
</body>
</html>