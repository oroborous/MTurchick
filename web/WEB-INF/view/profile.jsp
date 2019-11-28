<%--suppress HtmlUnknownTarget --%>
<%--suppress XmlDuplicatedId --%>
<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <%@include file="header.jsp" %>
    <div class="row" id="body">
        <div class="col">
            <ul class="list">
                <li><span id="name">Name: ${profile.name}</span></li>
                <li><span id="fave">Favorite Animal: ${profile.favorite}</span></li>
                <li><span id="motd">Motto: ${profile.motto}</span></li>
                <li>
                    <button class="btn-small btn-secondary" id="updTogBtn">Customize Profile</button>
                </li>
            </ul>
            <h3 class="row">
                Favorite Pictures:
            </h3>
            <div class="row">
                <c:forEach var="image" items="${profile.images}">
                    <div class="col-4">
                        <c:url var="imageLink" value="/detail">
                            <c:param name="imgId" value="${image.id}"/>
                        </c:url>
                        <a href="${imageLink}">
                            <h5 class="row">${image.title}</h5>
                            <div class="row"><img src="resources/img/${image.fileName}" alt="${image.title}"
                                                  class="gal-image"/></div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col mt-3">
            <form:form method="post"
                       action="${pageContext.request.contextPath}/updateProfile"
                       modelAttribute="profile"
                       id="edit" class="row">
                <form:hidden path="id"/>
                <div class="col">
                    <p class="row">
                        <label for="ed-name">Name:</label>
                        <form:input id="ed-name"
                                    maxlength="20"
                                    minlength="3"
                                    name="ed-name"
                                    size="40"
                                    type="text" path="name"/>
                    </p>
                    <p class="row">
                        <label for="ed-fave">Favorite Animal:</label>
                        <form:input id="ed-fave"
                                    maxlength="20"
                                    minlength="3"
                                    name="ed-fave"
                                    size="40"
                                    type="text" path="favorite"/>
                    </p>
                    <p class="row">
                        <label for="ed-motd">Motto:</label>
                        <form:input id="ed-motd"
                                    maxlength="50"
                                    minlength="5"
                                    name="ed-motd"
                                    size="35"
                                    type="text" path="motto"/>
                    </p>
                    <h5 class="row">
                        <button class="btn-small btn-secondary" id="ed-btn" type="submit">Submit Changes</button>
                    </h5>
                </div>
            </form:form>
            <div class="row" id="comments">
                <div class="col">
                    <h4 class="row">Message Board</h4>
                    <form:form method="post" action="${pageContext.request.contextPath}/addProfileComment"
                               modelAttribute="new_comment" cssClass="col">
                        <form:hidden path="profile" value="${profile.id}"/>
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
                        <c:forEach var="cmnt" items="${profile.comments}">
                            <c:url var="deleteLink" value="/removeProfileComment">
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
    </div>
    <%@include file="footer.jsp" %>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>