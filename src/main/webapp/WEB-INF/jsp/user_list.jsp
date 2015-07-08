<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="users" type="java.util.List"--%>
<t:layout tab="user_list">
    <h1><spring:message code="user.list"/></h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.getId()}"/></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</t:layout>