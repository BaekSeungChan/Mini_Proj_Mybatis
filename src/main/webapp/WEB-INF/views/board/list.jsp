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
    <h1>Community</h1>
    <h2><a href="/board/write">글 쓰기</a></h2>
    <table border="1" width="600">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${list}">
            <tr>
                <td>${board.id}</td>
                <td align="left">
                    <a href="/board/detail?id=${board.id}">
                            ${board.title}
                    </a>
                </td>
                <td>${board.writer}</td>
                <td>${board.writeTime}</td>
                <td>${board.readCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>