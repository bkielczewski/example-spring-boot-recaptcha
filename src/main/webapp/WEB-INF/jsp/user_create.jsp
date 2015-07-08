<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout tab="user_create">
<h1><spring:message code="user.create"/></h1>
<form:form method="post" action="/user_create.html" modelAttribute="form">
    <form:errors path="" element="div" class="alert alert-danger"/>
    <spring:bind path="id">
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:label path="id"><spring:message code="user.id"/></form:label>
        <form:input path="id" class="form-control"/>
        <form:errors path="id" class="help-block"/>
    </div>
    </spring:bind>
    <spring:bind path="password1">
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:label path="password1"><spring:message code="user.password1"/></form:label>
        <form:password path="password1" class="form-control"/>
        <form:errors path="password1" class="help-block"/>
    </div>
    </spring:bind>
    <spring:bind path="password2">
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:label path="password2"><spring:message code="user.password2"/></form:label>
        <form:password path="password2" class="form-control"/>
        <form:errors path="password2" class="help-block"/>
    </div>
    </spring:bind>
    <div>
        <input type="submit" class="btn btn-default" value="<spring:message code="save"/>"/>
    </div>
</form:form>
</t:layout>