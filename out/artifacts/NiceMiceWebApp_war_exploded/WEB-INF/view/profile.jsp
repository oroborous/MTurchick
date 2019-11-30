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
                        <label class="mr-2">Name:</label>
                        <form:input path="name" size="50"/>
                        <form:errors path="name" cssClass="error"/>
                    </p>
                    <p class="row">
                        <label class="mr-2">Favorite Animal:</label>
                        <form:input path="favorite" size="42"/>
                        <form:errors path="favorite" cssClass="error"/>
                    </p>
                    <p class="row">
                        <label class="mr-2">Motto:</label>
                        <form:input path="motto" size="50"/>
                        <form:errors path="motto" cssClass="error"/>
                    </p>
                    <h5 class="row">
                        <button class="btn-small btn-secondary" id="ed-btn" type="submit">Submit Changes</button>
                    </h5>
                </div>
            </form:form>
            <form:form method="post" action="${pageContext.request.contextPath}/addProfileComment"
                       modelAttribute="comment" id="profile-comment-list" cssClass="row">
                <form:hidden path="profile" value="${profile.id}"/>
                <div class="col">
                    <h4 class="row">Message Board</h4>
                    <div class="row">
                        <label class="mr-2">Comment:</label>
                        <form:input path="content" size="50"/>
                        <form:errors path="content" cssClass="error"/>
                    </div>
                    <div class="row mt-2">
                        <input class="btn-small btn-secondary"
                               id="createComBtn"
                               type="submit"
                               value="Post Comment">
                    </div>
                    <div class="row">
                        <ol class="list">
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
            </form:form>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>