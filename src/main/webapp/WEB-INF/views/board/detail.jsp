<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
</head>
<body>
<h1>${board.id}번 글 상세</h1>
<table border="1" width="500">
    <tr>
        <th width="25%">번호</th>
        <td>${board.id}</td>
    </tr>
    <tr>
        <th>제목</th>
        <td>${board.title}</td>
    </tr>
    <tr>
        <th>작성자</th>
        <td>${board.writer}</td>
    </tr>
    <tr height="200" valign="top">
        <th>내용</th>
        <td>${board.content}</td>
    </tr>
    <tr>
        <th>조회수</th>
        <td>${board.readCount}</td>
    </tr>

    <tr>
        <th>작성일</th>
        <td>
            <fmt:parseDate value="${board.writeTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedWriteTime" />
            <fmt:formatDate value="${parsedWriteTime}" pattern="yyyy'년' M'월' d'일' a h'시' m'분'" />
        </td>
    </tr>
    <tr>
        <th>변경일</th>
        <td>
            <fmt:parseDate value="${board.editTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedEditTime" />
            <fmt:formatDate value="${parsedEditTime}" pattern="yyyy'년' M'월' d'일' a h'시' m'분'" />
        </td>
    </tr>
</table>

<h2><button onclick="listFn()">목록</button></h2>
<h2><button onclick="updateFn()">수정</button></h2>
<h2><button>삭제</button></h2>


<script>
    const listFn = () => {
        location.href = "/board/list"
    }

    const updateFn = () => {
        location.href = "/board/edit?id=${board.id}"
    }

    const deleteFn = () => {
        location.href = "/board/delete?id=${board.id}"
    }
</script>
</body>
</html>