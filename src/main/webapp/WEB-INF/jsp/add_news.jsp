<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New News</title>
<link rel="stylesheet" href="css/stylesAddNews.css">
<script>
    function validateForm() {
        var title = document.getElementById("title").value;
        var brief = document.getElementById("brief").value;
        var date = document.getElementById("date").value;
        var category = document.getElementById("category").value;
        var author = document.getElementById("author").value;

        if (title == "" || brief == "" || date == "" || category == "" || author == "") {
            alert("All fields must be filled out");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
    <div class="form-container">
        <h2>Add New News</h2>
        <form id="news-form" action="Controller?command=add_news" method="post" onsubmit="return validateForm()">
            <label for="title">Title:</label> 
            <input type="text" id="title" name="title" required><br> 
            
            <label for="brief">Brief:</label>
            <textarea id="brief" name="brief" required></textarea><br> 
            
            <label for="image">Image URL:</label> 
            <input type="url" id="image" name="image"><br> 
            
            <label for="video">Video URL:</label> 
            <input type="url" id="video" name="video"><br> 
            
            <label for="date">Date:</label> 
            <input type="date" id="date" name="date" required><br> 
            
            <label for="category">Category:</label> 
            <select id="category" name="category" required>
                <option value="">Select category</option>
                <option value="business">Business</option>
                <option value="technology">Technology</option>
                <option value="sports">Sports</option>
                <option value="political">Political</option>
                <option value="weather">Weather</option>
                <option value="entertainment">Entertainment</option>
                <option value="live">Live News</option>
                <option value="good">Good News</option>
            </select><br>
            
            <label for="author">Author:</label> 
            <input type="text" id="author" name="author" required><br>
            
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>