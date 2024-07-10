<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit News List</title>
    <link rel="stylesheet" href="css/news-style.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="news-list-container">
    <jsp:useBean id="newsService" class="edu.training.web.service.impl.NewsServiceImpl" />
    <c:set var="newsList" value="${newsService.getAllNews()}" />
    
    <c:if test="${not empty newsList}">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="news" items="${newsList}">
                    <tr>
                        <td>${news.title}</td>
                        <td>
                            <!-- Edit Button -->
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="edit_news_form">
                                <input type="hidden" name="idNews" value="${news.idNews}">
                                <input type="submit" value="Edit">
                            </form>

                            <!-- Delete Button -->
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="delete_news">
                                <input type="hidden" name="idNews" value="${news.idNews}">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty newsList}">
        <p>No news available for editing.</p>
    </c:if>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>