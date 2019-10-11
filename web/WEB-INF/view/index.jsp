<%--
  Created by IntelliJ IDEA.
  User: mturchik
  Date: 10/11/2019
  Time: 7:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nice Mice - Home</title>
    <link crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/slate/bootstrap.min.css"
          integrity="sha384-FBPbZPVh+7ks5JJ70RJmIaqyGnvMbeJ5JQfEbW0Ac6ErfvEg9yG56JQJuMNptWsH"
          rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <header class="row">
        <div class="col"><img alt="logo" class="logo" src="resources/img/mouselogo.png"></div>
        <h1 class="col-11 text-left">Nice Mice</h1>
    </header>
    <nav class="row navbar navbar-expand navbar-dark bg-primary">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
        </ul>
    </nav>
    <div class="row" id="body">
        <div class="col-4">
            <p class="row justify-content-center">Accepting environment of mouse enthusiasts</p>
            <p class="row justify-content-center">Share art related to your lovable rodent</p>
            <p class="row justify-content-center">Loving and caring about <strong>SPRING MVC</strong></p>
        </div>
    </div>
    <footer class="row">
        <p class="col text-center">Created on 8/22/2019 by Mark Turchik</p>
    </footer>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>