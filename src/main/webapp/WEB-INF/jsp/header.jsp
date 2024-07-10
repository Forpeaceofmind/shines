<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<header>
	<nav>
		<ul>
			<li><a href="Controller?command=show_sport_news"><fmt:message
						key="menu.sport" /></a></li>
			<li><a href="Controller?command=show_live_news"><fmt:message
						key="menu.live" /></a></li>
			<li><a href="Controller?command=show_political_news"><fmt:message
						key="menu.political" /></a></li>
			<li><a href="Controller?command=show_business_news"><fmt:message
						key="menu.business" /></a></li>
			<li><a href="Controller?command=show_the_weather"><fmt:message
						key="menu.weather" /></a></li>
			<li><a href="Controller?command=show_good_news"><fmt:message
						key="menu.good" /></a></li>
		</ul>
	</nav>

	<nav>
		<div class="language-selector">
			<span><fmt:message key="language" /></span> <a
				href="Controller?command=localizations&lang=en"
				class="language-link"><fmt:message key="language.EN" /></a> <a
				href="Controller?command=localizations&lang=ru"
				class="language-link"><fmt:message key="language.RU" /></a>
		</div>
	</nav>

	<div>
		<c:if test="${not (param.authError eq null) }">
			<c:out value="${param.authError}" />
		</c:if>
		<c:if
			test="${not empty sessionScope.user and sessionScope.user.role eq 'admin'}">
			<a href="Controller?command=go_to_adding_news_page"
				class="btn btn-lg btn-danger btn-custom"><fmt:message
					key="button.addNews" /></a>
		</c:if>
		<c:if
			test="${not empty sessionScope.user}">
		<a href="Controller?command=do_logout"
			class="btn btn-lg btn-danger btn-custom"><fmt:message
				key="button.logout" /></a>
		</c:if>

		<c:if test="${empty sessionScope.user}">
			<a href="Controller?command=go_to_sign_in_page"><fmt:message
					key="button.signIn" /></a>
			<a href="Controller?command=go_to_registration_page"><fmt:message
					key="button.signUp" /></a>
		</c:if>
	</div>
</header>