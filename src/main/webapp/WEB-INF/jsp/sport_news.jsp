<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shhh Sport</title>
<link rel="stylesheet" href="css/news-style.css">
</head>
<body>


	<jsp:include page="header.jsp" />

	<div class="content">
		<h2>Sport News</h2>
		<ul>
			<c:forEach var="news" items="${sportNews}">
				<li>
					<h3>${news.title}</h3> 
					<c:if
						test="${not empty news.image}">
						<img src="${news.image}" alt="${news.title}"
							style="max-width: 100%; height: auto;">
					</c:if>
					<p>${news.brief}</p> 
					<c:if
						test="${not empty news.video}">
						<video width="560" height="315" controls>
							<source src="${news.video}" type="video/mp4">
		
						</video>
					</c:if>
					<p>Category: ${news.category}</p>
					<p>Author: ${news.authorName}</p>
				</li>
				
			</c:forEach>
		</ul>
		<c:import url="commet.jsp" />
	</div>

	<jsp:include page="footer.jsp" />

	<script src="js/news.js"></script>

</body>
</html>