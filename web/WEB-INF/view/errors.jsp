<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Nice Mice - Oops!</title>
    <link crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/slate/bootstrap.min.css"
          integrity="sha384-FBPbZPVh+7ks5JJ70RJmIaqyGnvMbeJ5JQfEbW0Ac6ErfvEg9yG56JQJuMNptWsH"
          rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
    <%@include file="header.jsp" %>
    <div class="row" id="body">
        <div class="col">
            <h3>Oops! An error occurred</h3>
            <p>${errorMessage}</p>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>
