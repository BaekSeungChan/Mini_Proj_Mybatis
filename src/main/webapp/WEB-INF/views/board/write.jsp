<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Write Page</title>
</head>
<body>
<h1>Write Page</h1>
<form id="writeForm" action="/board/write" method="post">
    <input type="text" id="title" name="title" placeholder="제목"><br><br>
    <input type="text" id="writer" name="writer" placeholder="작성자"><br><br>
    <textarea name="content" id="content" rows="15" placeholder="내용"></textarea><br><br>
    <button type="submit">등록</button>
</form>
</body>
</html>
