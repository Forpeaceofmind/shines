<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shhh live news</title>
<link rel="stylesheet" href="css/news-style.css">
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="content">
		<h2>Live News</h2>
		<ul>
			<c:forEach var="news" items="${liveNews}">
				<li>
					<h3>${news.title}</h3>

					<p>${news.brief}</p>

					<p>Category: ${news.category}</p>

				</li>

			</c:forEach>
		</ul>
		<c:import url="commet.jsp" />
	</div>

	<jsp:include page="footer.jsp" />

	<script src="js/news.js"></script>

</body>
</html>