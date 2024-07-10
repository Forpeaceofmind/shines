<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<c:if test="${not empty sessionScope.user}">
	<form id="comment-form">
		<label for="comment"><fmt:message key="label.comment" /></label>
		<textarea id="comment" name="comment" required></textarea>
		<input type="submit" value="<fmt:message key="input.submit" />">
	</form>
</c:if>