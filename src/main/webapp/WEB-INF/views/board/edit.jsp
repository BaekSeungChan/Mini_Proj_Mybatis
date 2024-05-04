<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
</head>
<body>
<h1>${board.id}번 게시글 수정</h1>
<form action="/board/edit" method="post">
    <input type="hidden" value="${board.id}" name="id">
    <input type="text" name="writer" placeholder="작성자" value="${board.writer}"><br><br>
    <input type="text" name="title" placeholder="제목" value="${board.title}"><br><br>
    <textarea  name="content" placeholder="내용" rows="5" >${board.content}</textarea>
    <button type="submit">수정</button>
</form>

</body>
</html>