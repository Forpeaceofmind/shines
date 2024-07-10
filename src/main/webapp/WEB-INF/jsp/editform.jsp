<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<c:if
	test="${not empty sessionScope.user and sessionScope.user.role eq 'admin'}">
	<form action="Controller" method="post" class="edit-news-form">
		<input type="hidden" name="command" value="edit_news"> <input
			type="hidden" name="idNews" value="${news.idNews}"> <input
			type="button" value="Edit" onclick="showEditForm(this)">
	</form>


	<form action="Controller" method="post">
		<input type="hidden" name="command" value="delete_news"> <input
			type="hidden" name="idNews" value="${news.idNews}"> <input
			type="submit" value="Delete">
	</form>


	<div class="edit-form" id="editForm_${news.idNews}">
		<h3>Edit News</h3>
		<form action="Controller" method="post"
			onsubmit="return submitEditForm(${news.idNews})">
			<input type="hidden" name="command" value="do_edit"> <input
				type="hidden" name="idNews" value="${news.idNews}"> <label
				for="editDate">Date:</label><br> <input type="text"
				id="editDate" name="date" value="${news.date}" required><br>
			<label for="editTitle">Title:</label><br> <input type="text"
				id="editTitle" name="title" value="${news.title}" required><br>
			<label for="editBrief">Brief:</label><br>
			<textarea id="editBrief" name="brief" rows="4" cols="50" required>${news.brief}</textarea>
			<br> <label for="editCategory">Category:</label><br> <input
				type="text" id="editCategory" name="category"
				value="${news.category}" required><br> <label
				for="editImage">Image:</label><br> <input type="text"
				id="editImage" name="image" value="${news.image}" required><br>
			<label for="editAuthorName">Author:</label><br> <input
				type="text" id="editAuthorName" name="author"
				value="${news.authorName}" required><br> <label
				for="editVideo">Video:</label><br> <input type="text"
				id="editVideo" name="video" value="${news.video}" required><br>
			<input type="submit" value="Save"> <input type="button"
				value="Cancel" onclick="cancelEdit(${news.idNews})">
		</form>
	</div>
</c:if>
