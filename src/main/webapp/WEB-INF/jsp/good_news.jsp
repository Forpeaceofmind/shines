<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shhhh Good News Only</title>
    <link rel="stylesheet" href="css/news-style.css">
    <style>
        .edit-form {
            display: none;
        }

        .edit-form.active {
            display: block;
        }
    </style>
</head>
<body>


    <c:import url="header.jsp" />

    <div class="content">
        <h2>Good News</h2>
        <ul>
            <c:forEach var="news" items="${goodNews}">
                <li>
                    <h3>${news.title}</h3>
                    <c:if test="${not empty news.image}">
                        <img src="${news.image}" alt="${news.title}" style="max-width: 100%; height: auto;">
                    </c:if>
                    <p>${news.brief}</p>

                    <p>Category: ${news.category}</p>
                    <p>Author: ${news.authorName}</p>

                   <c:import url="editform.jsp" />
                </li>
            </c:forEach>
        </ul>
        <c:import url="commet.jsp" />
    </div>

    <c:import url="footer.jsp" />

    <script src="js/news.js"></script>
    <script>
        function showEditForm(button) {
            var newsId = button.form.querySelector('input[name="idNews"]').value;
            var editForm = document.getElementById('editForm_' + newsId);
            editForm.classList.add('active');
        }

        function cancelEdit(newsId) {
            var editForm = document.getElementById('editForm_' + newsId);
            editForm.classList.remove('active');
        }

        function submitEditForm(newsId) {
            var editForm = document.getElementById('editForm_' + newsId);
            // Additional validation or processing can be added here
            return true; // or false to prevent form submission
        }
    </script>

</body>
</html>