<%@ tag description="Layout Template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="tab" required="false" type="java.lang.String" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<spring:url value="/public/css/all.css" />"/>
</head>
<body>
<div id="body">
    <ul class="nav nav-tabs">
        <li role="presentation" <c:if test="${tab == 'user_list'}">class="active"</c:if>>
            <a href="<spring:url value="/user_list.html" />"><spring:message code="user.list"/></a>
        </li>
        <li role="presentation" <c:if test="${tab == 'user_create'}">class="active"</c:if>>
            <a href="<spring:url value="/user_create.html" />"><spring:message code="user.create"/></a>
        </li>
    </ul>
    <div class="container">
        <jsp:doBody/>
    </div>
</div>
<script type="text/javascript" src="<spring:url value="/public/css/all.css" />"></script>
</body>
</html>