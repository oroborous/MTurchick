<%--
  Created by IntelliJ IDEA.
  User: mturchik
  Date: 9/13/2019
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nice Mice - Profile</title>
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
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Gallery</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Profile</a>
            </li>
        </ul>
    </nav>
    <div class="row" id="body">
        <div class="col">
            <ul class="list">
                <li><span id="name">Name: Name</span></li>
                <li><span id="fave">Favorite Animal: Fave</span></li>
                <li><span id="motd">Message of the Day: Motd</span></li>
                <li>
                    <h5>Update Profile:
                        <button class="btn btn-secondary" id="updTogBtn">Update</button>
                    </h5>
                </li>
            </ul>
            <ul class="list">
                <li><h5>Favorite Pictures:</h5></li>
                <li>
                    <table id="pro-fav-images">

                    </table>
                </li>
            </ul>
        </div>
        <div class="col">
            <form method="post" action="${pageContext.request.contextPath}/profile" id="edit" class="row">
                <ul class="list">
                    <li>
                        <label for="ed-name">Name:</label> <input id="ed-name"
                                                                  maxlength="20"
                                                                  minlength="3"
                                                                  name="ed-name"
                                                                  size="40"
                                                                  type="text" />
                    </li>
                    <li>
                        <label for="ed-fave">Favorite Animal:</label> <input id="ed-fave"
                                                                             maxlength="20"
                                                                             minlength="3"
                                                                             name="ed-fave"
                                                                             size="40"
                                                                             type="text" />
                    </li>
                    <li>
                        <label for="ed-motd">Message of the day:</label> <input id="ed-motd"
                                                                                maxlength="50"
                                                                                minlength="5"
                                                                                name="ed-motd"
                                                                                size="35"
                                                                                type="text" />
                    </li>
                    <li id="li-btn">
                        <h5>
                            Submit Changes: <button class="btn btn-secondary" id="ed-btn" type="submit">Submit</button>
                        </h5>
                    </li>
                </ul>
            </form>
            <div class="row" id="comments">
                <h4>Message Board</h4>
                <ul class="list" id="profile-comment-list">
                    <li>
                        <form method="post" action="Comment">
                            <label for="comment">Comment:</label> <input id="comment" name="comment"
                                                                         maxlength="100" minlength="10"
                                                                         size="40" type="text" required>
                            <button class="btn btn-secondary" id="createComBtn" type="submit">Post Comment</button>
                            <input type="hidden" value="p" name="hosttype" id="hosttype">
                        </form>
                    </li>

                </ul>
            </div>
        </div>
    </div>
    <footer class="row">
        <div class="col text-center">
            <p>Current Date is: <%= (new java.util.Date()).toString() %></p>
            <p>Created on 8/22/2019 by Mark Turchik</p>
        </div>
    </footer>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>