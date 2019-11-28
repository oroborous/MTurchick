<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
        </ul>
    </nav>
    <div class="row" id="body">
        <div class="col">
            <ul class="list">
                <li><span id="name">Name: ${profile.getName()}</span></li>
                <li><span id="fave">Favorite Animal: ${profile.getFavorite()}</span></li>
                <li><span id="motd">Motto: ${profile.getMotto()}</span></li>
                <li>
                    <button class="btn-small btn-secondary" id="updTogBtn">Customize Profile</button>
                </li>
            </ul>
            <ol class="list">
                <li><h5>Favorite Pictures:</h5></li>
                <c:forEach var="image" items="${profile.images}">
                    <li>
                        <a href="#">${image.title}</a>
                    </li>
                </c:forEach>
            </ol>
        </div>
        <div class="col">
            <form:form method="post"
                       action="${pageContext.request.contextPath}/updateProfile"
                       modelAttribute="profile"
                       id="edit" class="row">
                <form:hidden path="id"/>
                <ul class="list">
                    <li>
                        <label for="ed-name">Name:</label>
                        <form:input id="ed-name"
                                    maxlength="20"
                                    minlength="3"
                                    name="ed-name"
                                    size="40"
                                    type="text" path="name"/>
                        <form:errors path="name"/>
                    </li>
                    <li>
                        <label for="ed-fave">Favorite Animal:</label>
                        <form:input id="ed-fave"
                                    maxlength="20"
                                    minlength="3"
                                    name="ed-fave"
                                    size="40"
                                    type="text" path="favorite"/>
                        <form:errors path="favorite"/>
                    </li>
                    <li>
                        <label for="ed-motd">Motto:</label>
                        <form:input id="ed-motd"
                                    maxlength="50"
                                    minlength="5"
                                    name="ed-motd"
                                    size="35"
                                    type="text" path="motto"/>
                        <form:errors path="motto"/>
                    </li>
                    <li id="li-btn">
                        <h5>
                            Submit Changes:
                            <button class="btn-small btn-secondary" id="ed-btn" type="submit">Submit</button>
                        </h5>
                    </li>
                </ul>
            </form:form>
            <div class="row" id="comments">
                <h4>Message Board</h4>
                <ol class="list" id="profile-comment-list">
                    <li>
                        <form:form method="post" action="${pageContext.request.contextPath}/addProfileComment"
                                   modelAttribute="new_comment">
                            <form:hidden path="profile" value="${profile.id}"/>
                            <label for="comment">Comment:</label>
                            <form:input id="comment" name="comment"
                                        path="content"
                                        maxlength="100" minlength="10"
                                        size="40" type="text"/>
                            <button class="btn-small btn-secondary" id="createComBtn" type="submit">Post Comment
                            </button>
                        </form:form>
                    </li>
                    <hr/>
                    <c:forEach var="cmnt" items="${profile.comments}">
                        <li>
                            <form:form method="post"
                                       action="${pageContext.request.contextPath}/removeProfileComment"
                                       modelAttribute="del_comment">
                                <form:hidden path="id" value="${cmnt.id}"/>
                                <button type="submit" class="btn-small btn-warning">-</button>
                                - ${cmnt.content}
                            </form:form>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
    <footer class="row">
        <div class="col text-center">
            <p>Current Date is: <%= (new java.util.Date()).toString() %>
            </p>
            <p>Created on 8/22/2019 by Mark Turchik</p>
        </div>
    </footer>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>