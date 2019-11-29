<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nice Mice - Gallery</title>
    <link crossorigin="anonymous"
          href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/slate/bootstrap.min.css"
          integrity="sha384-FBPbZPVh+7ks5JJ70RJmIaqyGnvMbeJ5JQfEbW0Ac6ErfvEg9yG56JQJuMNptWsH"
          rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>
    <div class="row">
        <form:form action="${pageContext.request.contextPath}/searchImages" method="GET" cssClass="my-2">
            <label for="searchTerm">Search images by title:</label>
            <input type="search" id="searchTerm" name="searchTerm" class="mr-2"/>
            <input type="submit" value="Search" class="btn-small btn-info"/>
        </form:form>
    </div>
    <div class="row">
        <c:forEach var="image" items="${images}">
            <c:url var="detailLink" value="/detail">
                <c:param name="imgId" value="${image.id}"/>
            </c:url>
            <div class="col-3">
                <a href="${detailLink}">
                    <div class="row">${image.title}</div>
                    <div class="row"><img src="resources/img/${image.fileName}"
                                          alt="${image.description}"
                                          class="gal-image"></div>
                </a>
            </div>
        </c:forEach>
    </div>
    <%@include file="footer.jsp" %>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>