<%--suppress HtmlUnknownTarget --%>
<%--
  Created by IntelliJ IDEA.
  User: mturchik
  Date: 11/27/2019
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
            <a class="nav-link" href="${pageContext.request.contextPath}/gallery">Gallery</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
        </li>
    </ul>
</nav>
</body>
</html>
