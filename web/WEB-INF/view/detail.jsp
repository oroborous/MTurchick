<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nice Mice - Picture Details</title>
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
            <c:url var="faveLink" value="/favoriteImage">
                <c:param name="imageId" value="${image.id}"/>
                <c:param name="profileId" value="102"/><!-- TODO: HARDCODED PROFILE -->
            </c:url>
            <c:url var="unFaveLink" value="/unFavoriteImage">
                <c:param name="imageId" value="${image.id}"/>
                <c:param name="profileId" value="102"/><!-- TODO: HARDCODED PROFILE -->
            </c:url>
            <div class="row">
                <c:choose>
                    <c:when test="${!image.isFavoriteBy(102)}">
                        <a href="${faveLink}">
                            <button class="btn-small btn-success m-2">Favorite</button>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${unFaveLink}">
                            <button class="btn-small btn-danger m-2">Un-Favorite</button>
                        </a>
                    </c:otherwise>
                </c:choose>
                <h3>Title: ${image.title}</h3>
            </div>
            <div class="row">
                <img alt="detail-image" id="detail-image" src="resources/img/${image.fileName}">
            </div>
            <p class="row">Description: ${image.description}</p>
        </div>
        <div class="col ml-3">
            <div class="row" id="comments">
                <h4>Message Board</h4>
                <form:form method="post"
                           action="${pageContext.request.contextPath}/addImageComment"
                           modelAttribute="new_comment" cssClass="col">
                    <form:hidden path="image" value="${image.id}"/>
                    <div class="row">
                        <label for="comment">Comment:</label>
                        <form:input id="comment" name="comment"
                                    path="content"
                                    maxlength="100" minlength="10"
                                    size="50"
                                    type="text"/>
                    </div>
                    <div class="row mt-2">
                        <button class="btn-small btn-secondary"
                                id="createComBtn"
                                type="submit">
                            Post Comment
                        </button>
                    </div>
                </form:form>
                <ol class="list" id="profile-comment-list">
                    <c:forEach var="cmnt" items="${image.comments}">
                        <c:url var="deleteLink" value="/removeImageComment">
                            <c:param name="commentId" value="${cmnt.id}"/>
                        </c:url>
                        <li>
                            <a href="${deleteLink}">[ :( ]</a>~${cmnt.content}
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>