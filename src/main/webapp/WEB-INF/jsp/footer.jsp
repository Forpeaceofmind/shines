<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<footer>
    <nav>
        <ul>
            <li><a href="#about"><fmt:message key="menu.about"/></a></li>
            <li><a href="#contact"><fmt:message key="menu.contact"/></a></li>
            <li><a href="#help"><fmt:message key="menu.help"/></a></li>
        </ul>
    </nav>
</footer>