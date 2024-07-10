<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shhhh Weather</title>
<link rel="stylesheet" href="css/news-style.css">
</head>
<body>


	<c:import url="header.jsp" />



	<div class="content">
		<div style="text-align: center;">
			<iframe
				src="https://radar.weather.gov/?settings=v1_eyJhZ2VuZGEiOnsiaWQiOm51bGwsImNlbnRlciI6Wy05NSwzN10sImxvY2F0aW9uIjpudWxsLCJ6b29tIjo0fSwiYW5pbWF0aW5nIjpmYWxzZSwiYmFzZSI6InN0YW5kYXJkIiwiYXJ0Y2MiOmZhbHNlLCJjb3VudHkiOmZhbHNlLCJjd2EiOmZhbHNlLCJyZmMiOmZhbHNlLCJzdGF0ZSI6ZmFsc2UsIm1lbnUiOnRydWUsInNob3J0RnVzZWRPbmx5IjpmYWxzZSwib3BhY2l0eSI6eyJhbGVydHMiOjAuOCwibG9jYWwiOjAuNiwibG9jYWxTdGF0aW9ucyI6MC44LCJuYXRpb25hbCI6MC42fX0%3D"
				width="100%" height="600px" frameborder="0"></iframe>
		</div>
		
		<c:import url="commet.jsp" />
	</div>

	<c:import url="footer.jsp" />

	<script src="js/news.js"></script>

</body>
</html>

