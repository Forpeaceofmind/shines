<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title"/></title>
    <link rel="stylesheet" href="css/news-style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="video-container">
        <iframe src="https://www.youtube.com/embed/BHY0FxzoKZE"
            frameborder="0" allowfullscreen></iframe>
    </div>

    <jsp:include page="footer.jsp" />
    <script src="js/news.js"></script>
</body>
</html>