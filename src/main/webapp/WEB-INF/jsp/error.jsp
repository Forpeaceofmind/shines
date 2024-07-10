<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> <!-- Указывает, что это страница обработки ошибок -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>
    <div>
        <h1>Error Occurred</h1>
        <p>Sorry, an unexpected error occurred. Please try again later.</p>
        <h2>Error Details:</h2>
        <p><strong>Message:</strong> ${exception.message}</p>
        <h2>Stack Trace:</h2>
        <pre>${exception.stackTrace}</pre>
    </div>
</body>
</html>