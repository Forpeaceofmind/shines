<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title"/></title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
    background-color: #d5f4e6;
    font-family: Arial, sans-serif;
}
.container {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
    <div class="container mt-5">
        <h2><fmt:message key="register.title"/></h2>
        <form id="registrationForm" action="Controller" method="post">
            <input type="hidden" name="command" value="do_registration" />
            <div class="form-group">
                <label for="username"><fmt:message key="register.username"/></label>
                <input type="text" class="form-control" id="username" name="login" required>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="register.password"/></label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="name"><fmt:message key="register.name"/></label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="dob"><fmt:message key="register.dob"/></label>
                <input type="date" class="form-control" id="dob" name="birthDate" required>
            </div>
            <div class="form-group">
                <label for="country"><fmt:message key="register.country"/></label>
                <select class="form-control" id="country" name="residence" required>
                    <option value=""><fmt:message key="register.selectCountry"/></option>
                    <option value="Russia"><fmt:message key="country.russia"/></option>
                    <option value="USA"><fmt:message key="country.usa"/></option>
                    <option value="Germany"><fmt:message key="country.germany"/></option>
                    <option value="France"><fmt:message key="country.france"/></option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="register.submit"/></button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>