<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Form</title>
    <style>
        .colortext {
            color: darkred;
        }
    </style>
    <link rel='stylesheet prefetch' href='/css/bootstrap.min.css'>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<p><br/></p>
<p align="center"><label class="colortext"> ${messageRegister} </label></p>
<div class="wrapper">
    <form class="form-signin" method="POST" name="registerForm" action="cruisecompany">
        <input type="hidden" name="command" value="registerButton"/>
        <h2 class="form-signin-heading"><p align="center">${registerLoc}</p></h2>
        ${userLoginRegFormLoc}
        <br/>
        <input type="text" class="form-control" name="loginRegister" value="${login}"/>
        ${passLoc}
        <br/>
        <input type="password" class="form-control" name="passwordRegister" value="${pass}"/>
        ${fnameRegFormLoc}
        <br/>
        <input type="text" class="form-control" name="firstNameRegister" value="${first}"/>
        ${mnameRegFormLoc}
        <br/>
        <input type="text" class="form-control" name="middleNameRegister" value="${middle}"/>
        ${lnameRegFormLoc}
        <br/>
        <input type="text" class="form-control" name="lastNameRegister" value="${last}"/> <br/>
        <input type="submit" class="btn btn-lg btn-primary btn-block" name="registerBtn" value="${registerLoc}"/> <br/>
    </form>
</div>
</body>
</html>
