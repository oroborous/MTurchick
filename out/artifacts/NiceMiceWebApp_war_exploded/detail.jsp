<%--
  Created by IntelliJ IDEA.
  User: mturchik
  Date: 9/17/2019
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nice Mice - Picture Details</title>
    <link crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/slate/bootstrap.min.css"
          integrity="sha384-FBPbZPVh+7ks5JJ70RJmIaqyGnvMbeJ5JQfEbW0Ac6ErfvEg9yG56JQJuMNptWsH"
          rel="stylesheet">
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <header class="row">
        <div class="col"><img alt="logo" class="logo" src="img/mouselogo.png"></div>
        <h1 class="col-11 text-left">Nice Mice</h1>
    </header>
    <nav class="row navbar navbar-expand navbar-dark bg-primary">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="index.html">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="gallery.html">Gallery</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="gallery.jsp">Gallery(JSP)</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile.html">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile.jsp">Profile(JSP)</a>
            </li>
        </ul>
    </nav>
    <div class="row" id="body">
        <div class="col">
            <img alt="detail-image" id="detail-image" src="img/1.jpeg">
        </div>
        <div class="col">
            <ul class="list" id="detail-comment-list">
                <li>
                    <form>
                        <label for="ed-comment">Comment:</label> <input id="ed-comment" maxlength="100" minlength="10"
                                                                        required size="40" type="text">
                        <input
                                class="btn btn-secondary" content="Submit" id="createComBtn" readonly type="submit">
                    </form>
                </li>
                <li>
                    <h4>
                        <button class="btn btn-success" id="detailFavBtn">Favorite</button>
                    </h4>
                </li>
                <li>
                    <h6 class="comment">3) I love it!</h6>
                </li>
                <li>
                    <h6 class="comment">2) I'm sorta over it...</h6>
                </li>
                <li>
                    <h6 class="comment">1) I'm done here.</h6>
                </li>
            </ul>
        </div>
    </div>
    <footer class="row">
        <p class="col text-center">Created on 8/22/2019 by Mark Turchik</p>
    </footer>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>